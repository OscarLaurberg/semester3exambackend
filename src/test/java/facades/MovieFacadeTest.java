/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.CombinedMovieInfoDTO;
import dtos.MovieDTO;
import dtos.MoviePosterDTO;
import entity.Movie;
import errorhandling.NotFoundException;
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
public class MovieFacadeTest {

    private static EntityManagerFactory entityManagerFactory;
    private static MovieFacade movieFacade;
    private static RequestFacade requestFacade;
    private static Movie m1, m2;
    private static MovieDTO md1, md2;

    public MovieFacadeTest() {
    }

    @BeforeAll        
    public static void setUpClass() {
        entityManagerFactory = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        movieFacade = MovieFacade.getMovieFacade(entityManagerFactory);
        requestFacade = RequestFacade.getRequestFacade(entityManagerFactory);
        m1 = new Movie("Fast & Furious 2000", 2022, "They are driving around, that's pretty much it","Sly himself","Scifi, horror","Arnold and the gang","posterland.dk");
        m2 = new Movie("Bamses Venner", 1998, "Bamse bager boller og Kylling griller","Torben Due","Børn","Bamse,Kylling,","posterland.dk");

    }


    @BeforeEach
    public void setUp() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            //entityManager.createNamedQuery("Person.deleteAllRows").executeUpdate();
            entityManager.persist(m1);
            entityManager.persist(m2);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        md1 = new MovieDTO(m1);
        md2 = new MovieDTO(m2);
    }
    
    @Test
    public void testGetAllMovies() throws NotFoundException{
        int expected = 2;
        int result = movieFacade.getAllMovies().size();
        assertEquals(expected,result);
    }

   /* @Test
       public void testGetByTitle_with_non_existing_title(){
           String title = "asfdasdsaæøå";
           assertThrows(NotFoundException.class, () -> {
               movieFacade.getMovieByTitle(title);
           });
    }*/
       
       @Test
       public void testGetByTitle_with_existing_title() throws NotFoundException{
           String expected = m1.getTitle();
           String result = movieFacade.getMovieByTitle(expected).get(0).getTitle();
           assertEquals(expected, result);
       }
         @Test
   public void testCreateMovie_with_invalid_input(){
          MovieDTO m3 = new MovieDTO(null, 2022, "They are driving around, that's pretty much it",null,"Scifi, horror","Arnold and the gang",null);
          assertThrows(Exception.class, () -> {
              movieFacade.createMovie(m3);
          });
   }
   
   @Test
   public void testCreateMovie_with_valid_input() throws NotFoundException{
       int expected = movieFacade.getAllMovies().size()+1;
       MovieDTO m3 = new MovieDTO("Frisk og frejdig", 2022, "They are driving around, that's pretty much it","hep-hey","Scifi, horror","Arnold and the gang","posterland.dk");
       movieFacade.createMovie(m3);
       int result = movieFacade.getAllMovies().size();
       assertEquals(expected, result);
   }
   
   @Test
   public void testcheckIfMovieExistsInDbAndCreateRequest() throws NotFoundException{
        MovieDTO m3 = new MovieDTO("Frisk og frejdig", 2022, "They are driving around, that's pretty much it","hep-hey","Scifi, horror","Arnold and the gang","posterland.dk");
        MoviePosterDTO poster = new MoviePosterDTO("eyo","www.posterland.dk");
        CombinedMovieInfoDTO combinedInfo = new CombinedMovieInfoDTO(m3,poster);
        movieFacade.checkIfMovieExistsInDbAndCreateRequest(combinedInfo);
        int expected = 3;
        int result = movieFacade.getAllMovies().size();
        int expectedd = 1;
        
        int resultt = requestFacade.getRequestAmountByMovieTitle("Frisk og frejdig");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(resultt);
        assertEquals(expected, result);
        assertEquals(expectedd, resultt);
        
   }




    
}
