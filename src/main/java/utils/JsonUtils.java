/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dtos.CombinedMovieInfoDTO;
import dtos.MovieInfoDTO;
import dtos.MoviePosterDTO;

/**
 *
 * @author oscar
 */
public class JsonUtils {

    public JsonUtils() {
    }
    
    

    private final Gson GSON = new Gson();

    public String getNestedJsonObject(JsonObject obj, String rater) {

        String rating = "";

        JsonObject jsonObject = GSON.fromJson(obj, JsonObject.class);
        switch (rater) {
            case "imdb":
                JsonObject imdbJson = (JsonObject) jsonObject.get("imdb");
                rating = imdbJson.get("imdbRating").getAsString();
                break;
            case "metacritics":
                JsonObject mcJson = (JsonObject) jsonObject.get("metacritics");
                rating = mcJson.get("metacritic").getAsString();
                break;
            case "tomatoes":
                JsonObject tomatoJson = (JsonObject) jsonObject.get("tomatoes");
                JsonObject viewer = (JsonObject) tomatoJson.get("viewer");
                rating = viewer.get("rating").getAsString();
                break;
            default:
                break;
        }
        return rating;
    }
}

