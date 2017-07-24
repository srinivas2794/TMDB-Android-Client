package com.example.srinivas.tmdb.api;

/**
 * This is created by Srinivas on 7/18/2017.
 */

public class ApiUtils {
    private static String BASE_URL = "https://api.themoviedb.org/";

    public static TMDBService getTMDBService() {
        return RetrofitClient.getClient(BASE_URL).create(TMDBService.class);
    }
}
