/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author oscar
 */
public class JsonUtilsTest {

    private static final JsonUtils JSONUTILS = new JsonUtils();
    private final Gson GSON = new Gson();

    @Test
    public void testGetNestedJsonObject() {
        String jsonString = "{\"title\":\"Die Hard\",\"tomatoes\":{\"viewer\":{\"rating\":3.9,\"numReviews\":57005,\"meter\":94},\"critic\":{\"rating\":8.4,\"numReviews\":64,\"meter\":92}}}";
        JsonObject obj = new JsonParser().parse(jsonString).getAsJsonObject();
        String expected = "3.9";
        String result = JSONUTILS.getNestedJsonObject(obj, "tomatoes");
        assertEquals(expected, result);

    }

}
