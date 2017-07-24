package com.example.srinivas.tmdb.api.data_model.responses;

import android.support.annotation.NonNull;

import com.example.srinivas.tmdb.api.data_model.items.Genre;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This is created by Srinivas on 7/17/2017.
 */

public class GenreResponse {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

}
