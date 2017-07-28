package com.example.srinivas.tmdb;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;

public class ActivityMain extends AppCompatActivity {

    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, new FragmentGenreList()).commit();
    }


}
