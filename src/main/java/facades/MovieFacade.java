/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MovieDTO;
import entity.Movie;
import errorhandling.NotFoundException;
import errorhandling.UserException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author oscar
 */
public class MovieFacade {
    
    private static MovieFacade instance;
    private static EntityManagerFactory entityManagerFactory;
    
    public MovieFacade(){
        
    }
     public static MovieFacade getMovieFacade(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            MovieFacade.entityManagerFactory = entityManagerFactory;
            instance = new MovieFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
    
    
    public List<MovieDTO> getAllMovies() throws NotFoundException{
        EntityManager entityManager = getEntityManager();
        try{
            List<Movie> movies = entityManager.createNamedQuery("Movie.getAll", Movie.class)
                    .getResultList();
            return convertToMovieDTOList(movies);
        }catch (NoResultException e){
            throw new NotFoundException();
                }finally{
            entityManager.close();
        }
        
    }
    
    public MovieDTO createMovie(MovieDTO movieDTO) {
        EntityManager entityManager = getEntityManager();
        Movie movie = new Movie(movieDTO);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(movie);
            entityManager.getTransaction().commit();
        }finally{
            entityManager.close();
        }
    return movieDTO;
}
    
    public MovieDTO getMovieByTitle(String title) throws NotFoundException{
        EntityManager entityManager = getEntityManager();
        try{
            Movie movie = entityManager.createNamedQuery("Movie.getByTitle", Movie.class)
                    .setParameter("title", title).getSingleResult();
            return new MovieDTO (movie);
        }catch (NoResultException e) {
            throw new NotFoundException();
        }finally{
            entityManager.close();
        }
        
        
    }
    
    
        private List<MovieDTO> convertToMovieDTOList(List<Movie> movies) {
        List<MovieDTO> dtos = new ArrayList<>();
        movies.forEach((movie) -> {
            dtos.add(new MovieDTO(movie));
        });
        return dtos;
    }
}