/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RequestDTO;
import entity.Request;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

/**
 *
 * @author oscar
 */
public class RequestFacadeTest {

    private static EntityManagerFactory emf;
    private static RequestFacade requestFacade;
    private static Request r1, r2;
    private static RequestDTO rd1, rd2;

    public RequestFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        requestFacade = RequestFacade.getRequestFacade(emf);
        r1 = new Request("Indiana Jones");
        r2 = new Request("Testing 1-2-3");
    }

    @BeforeEach
    public void setUp() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            entityManager.createNamedQuery("Request.deleteAllRows").executeUpdate();
            entityManager.persist(r1);
            entityManager.persist(r2);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        rd1 = new RequestDTO(r1);
        rd2 = new RequestDTO(r2);
    }

    @Test
    public void testGetAll() {
        List<RequestDTO> requestDTOs = requestFacade.getAll();
        String expectedTitle = r1.getTitle();
        String resultTitle = requestDTOs.get(0).getTitle();
        assertEquals(expectedTitle, resultTitle);
    }

    @Test
    public void testGetAllAgain() {
        int expectedAmount = 2;
        int resultAmount = requestFacade.getAll().size();

        assertEquals(expectedAmount, resultAmount);
    }

    @Test
    public void testCreateRequest(){
        int expected = requestFacade.getAll().size()+1;
        RequestDTO requestDTO = new RequestDTO("Skammerens datter");
        requestFacade.createRequest(requestDTO);
        int result = requestFacade.getAll().size();
        assertEquals(expected, result);   
    }
    
    @Test 
    public void testGetRequestAmountByMovieTitle(){
        String title = "Skammerens datter";
        int expected = 4;
        RequestDTO requestDTO1 = new RequestDTO(title);
        requestFacade.createRequest(requestDTO1);
        RequestDTO requestDTO2 = new RequestDTO(title);
        requestFacade.createRequest(requestDTO2);
        RequestDTO requestDTO3 = new RequestDTO(title);
        requestFacade.createRequest(requestDTO3);
        
        int result = requestFacade.getRequestAmountByMovieTitle(title);
        
        
    }


}
