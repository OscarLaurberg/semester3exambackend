/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dtos.CombinedMovieInfoDTO;
import dtos.MovieInfoDTO;
import dtos.MoviePosterDTO;
import errorhandling.NotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;

/**
 * REST Web Service
 *
 * @author oscar
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Exam API",
                version = "0.1",
                description = "This API is use as a base for building a backend for a separate Frontend",
                contact = @Contact(name = "Oscar", email = "cph-ol38@cphbusiness.dk")
        ),
        tags = {
            @Tag(name = "movies", description = "API related to movies"),
            @Tag(name = "login", description = "API related to Login"),},
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/exambackend"
            ),
            @Server(
                    description = "Server API",
                    url = "https://www.laurberg.codes/sem3exambackend/"
            )
        }
)
@Path("movie-info")
public class MovieInfoResource {

    private final String MOVIE_INFO_URL = "https://ex2-0-2-0.mydemos.dk/movieInfo/";
    private final String MOVIE_POSTER_URL = "https://ex2-0-2-0.mydemos.dk/moviePoster/";
    private final Gson GSON = new Gson();
    @Context
    private UriInfo context;

    @Operation(summary = "Getting two random jokes, one Chuck Norris and one Dad Joke",
            tags = {"jokes"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = dtos.CombinedMovieInfoDTO.class))),
                @ApiResponse(responseCode = "200", description = "The requested movie info"),
                @ApiResponse(responseCode = "404", description = "Movie not found")})
    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieInfoFromTitle(@PathParam("title") String title) throws IOException, NotFoundException {
        String movieInfoJson = HttpUtils.fetchData(MOVIE_INFO_URL + title);
        String moviePosterJson = HttpUtils.fetchData(MOVIE_POSTER_URL + title);
        MovieInfoDTO movieInfo = GSON.fromJson(movieInfoJson, MovieInfoDTO.class);
        MoviePosterDTO posterInfo = GSON.fromJson(moviePosterJson, MoviePosterDTO.class);
        CombinedMovieInfoDTO combinedInfo = new CombinedMovieInfoDTO(movieInfo, posterInfo);
        String combinedJson = GSON.toJson(combinedInfo);
        return combinedJson;
      
        

    }

    /**
     * Creates a new instance of MovieInfoResource
     */
    public MovieInfoResource() {
    }

    /**
     * Retrieves representation of an instance of rest.MovieInfoResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}

    

/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() throws IOException {
        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        String dad = HttpUtils.fetchData("https://icanhazdadjoke.com/");
        ChuckJokeDTO chuckJoke = GSON.fromJson(chuck, ChuckJokeDTO.class);
        DadJokeDTO dadJoke = GSON.fromJson(dad, DadJokeDTO.class);
        CombinedJokesDTO combined = new CombinedJokesDTO(chuckJoke,dadJoke);
        String combinedJson = GSON.toJson(combined);
        return combinedJson;
    }*/