/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RequestDTO;
import entity.Request;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author oscar
 */
public class RequestFacade {
    
     private static RequestFacade instance;
    private static EntityManagerFactory entityManagerFactory;
    
     public static RequestFacade getRequestFacade(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            RequestFacade.entityManagerFactory = entityManagerFactory;
            instance = new RequestFacade();
        }
        return instance;
    }
     
     private EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();
}

     public List<RequestDTO> getAll(){
         EntityManager entityManager = getEntityManager();
         try{
             List<Request> requests = entityManager.createNamedQuery("Request.getAll").getResultList();
             return convertToRequestDTOList(requests);
         }finally{
             entityManager.close();
         }
         
     }
     
     public RequestDTO createRequest(RequestDTO requestDTO){
         EntityManager entityManager = getEntityManager();
         Request request = new Request(requestDTO);
         try{
             entityManager.getTransaction().begin();
             entityManager.persist(request);
             entityManager.getTransaction().commit();
                     
         }finally{
             entityManager.close();
         }
         requestDTO.setId(request.getId());
         return requestDTO;
         
     }
     
     
     public int getRequestAmountByMovieTitle(String title){
         EntityManager entityManager = getEntityManager();
         try{
             int amount = entityManager.createNamedQuery("Request.getByTitle",Request.class).setParameter("title", title).getResultList().size();
             return amount;
         } finally{
             entityManager .close();
         }
     }
     
         private List<RequestDTO> convertToRequestDTOList(List<Request> requests) {
        List<RequestDTO> dtos = new ArrayList<>();
        requests.forEach(request -> {dtos.add(new RequestDTO(request));});
        return dtos;
    }
    
}
