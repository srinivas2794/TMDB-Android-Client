package com.example.srinivas.tmdb.api;

import java.util.HashMap;
import java.util.Map;

/**
 * This is created by Srinivas on 7/18/2017.
 */

public class TMDBApiUtils {
    /**
     * base url for tmdb api calls
     */
    private static String BASE_URL = "https://api.themoviedb.org/";
    private static Map<String, String> defaultQueryParams = new HashMap<>();

    static {
        defaultQueryParams.put("api_key", "150d4acea63bc73968317383bfeacb23");
    }

    public static TMDBService getTMDBService() {
        return RetrofitClient.getClient(BASE_URL, defaultQueryParams).create(TMDBService.class);
    }
}
