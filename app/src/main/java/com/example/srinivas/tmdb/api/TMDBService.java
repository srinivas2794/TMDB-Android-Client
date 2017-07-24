package com.example.srinivas.tmdb.api;

import com.example.srinivas.tmdb.api.data_model.responses.GenreResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This is created by Srinivas on 7/18/2017.
 */

public interface TMDBService {
    @GET("3/genre/movie/list")
    Call<GenreResponse> getGenres();
}
