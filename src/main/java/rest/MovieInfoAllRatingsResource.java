/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dtos.CombinedMovieInfoDTO;
import dtos.MovieDTO;
import dtos.MoviePosterDTO;
import errorhandling.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;

/**
 * REST Web Service
 *
 * @author oscar
 */
@Path("movie-info-all-ratings")
public class MovieInfoAllRatingsResource {

    @Context
    private UriInfo context;

    private final String MOVIE_INFO_URL = "https://ex2-0-2-0.mydemos.dk/movieInfo/";
    private final String MOVIE_POSTER_URL = "https://ex2-0-2-0.mydemos.dk/moviePoster/";
    private final String MOVIE_IMDB_RATING_URL = "https://ex2-0-2-0.mydemos.dk/ratings/";
    private final Gson GSON = new Gson();
    private final JsonUtils jsonUtils = new JsonUtils();

    @Operation(summary = "Information about provided movie title with imdb rating",
            tags = {"movies"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = dtos.CombinedMovieInfoDTO.class))),
                @ApiResponse(responseCode = "200", description = "The requested movie info"),
                @ApiResponse(responseCode = "404", description = "Movie not found")})
    @GET
    @RolesAllowed({"admin","user"})
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRatingsMovieInfoFromTitle(@PathParam("title") String title) throws IOException {
        String movieInfoJson = HttpUtils.fetchData(MOVIE_INFO_URL + title);
        String moviePosterJson = HttpUtils.fetchData(MOVIE_POSTER_URL + title);
        String allRatings = HttpUtils.fetchData(MOVIE_IMDB_RATING_URL + title + "/imt");
        JsonObject jsonObject = GSON.fromJson(allRatings,JsonObject.class);
        String imdbRating = jsonUtils.getNestedJsonObject(jsonObject, "imdb");
        String tomatoRating = jsonUtils.getNestedJsonObject(jsonObject, "tomatoes");
        String metaRating = jsonUtils.getNestedJsonObject(jsonObject,"metacritics");
        MovieDTO movieInfo = GSON.fromJson(movieInfoJson, MovieDTO.class);
        MoviePosterDTO posterInfo = GSON.fromJson(moviePosterJson, MoviePosterDTO.class);
        CombinedMovieInfoDTO combinedInfo = new CombinedMovieInfoDTO(movieInfo, posterInfo, imdbRating, tomatoRating, metaRating);
        String combinedJson = GSON.toJson(combinedInfo);
        return combinedJson;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

}
