/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import errorhandling.NotFoundException;
import facades.RequestFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import javax.annotation.security.RolesAllowed;

/**
 * REST Web Service
 *
 * @author oscar
 */
@Path("movie-count")
public class MovieCountResource {

    private final String MOVIE_INFO_URL = "https://ex2-0-2-0.mydemos.dk/movieInfo/";
    private final String MOVIE_POSTER_URL = "https://ex2-0-2-0.mydemos.dk/moviePoster/";
    private final Gson GSON = new Gson();

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final RequestFacade FACADE = RequestFacade.getRequestFacade(EMF);

    @Context
    private UriInfo context;

    @Operation(summary = "Get the count of requests for a specific movie title",
            tags = {"movie"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = dtos.CombinedMovieInfoDTO.class))),
                @ApiResponse(responseCode = "200", description = "The requested movie info"),
                @ApiResponse(responseCode = "404", description = "Movie not found")})
    @GET
    @RolesAllowed("admin")
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getMovieRequstCount(@PathParam("title") String title) throws IOException, NotFoundException {
        return FACADE.getRequestAmountByMovieTitle(title);

    }

    /**
     * Creates a new instance of MovieCountResource
     */
    public MovieCountResource() {
    }

    /**
     * Retrieves representation of an instance of rest.MovieCountResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MovieCountResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
