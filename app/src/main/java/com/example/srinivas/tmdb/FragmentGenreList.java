package com.example.srinivas.tmdb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srinivas.tmdb.api.ApiUtils;
import com.example.srinivas.tmdb.api.data_model.items.Genre;
import com.example.srinivas.tmdb.api.data_model.responses.GenreResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is created by Srinivas on 7/16/2017.
 */

public class FragmentGenreList extends Fragment {

    @BindView(R.id.genre_list)
    RecyclerView recyclerView;

    @BindView(R.id.genre_progress)
    ContentLoadingProgressBar progressBar;

    GenreAdapter genreAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_genre_list, container, false);
        ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        genreAdapter = new GenreAdapter();
        recyclerView.setAdapter(genreAdapter);

        getGenres();

        return v;
    }

    private void getGenres() {

        progressBar.show();
        ApiUtils.getTMDBService().getGenres().enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenreResponse> call, @NonNull Response<GenreResponse> response) {
                Log.e("Network", "Succeded");
                progressBar.hide();
                if (response.isSuccessful()) {
                    Log.e("Network", "Succeded too");
                    List<Genre> genres = response.body().getGenres();
                    genreAdapter.addItems(genres);
                } else {

                    Log.e("Network", String.valueOf(response.code()));
                    Log.e("Network", response.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenreResponse> call, @NonNull Throwable t) {
                progressBar.hide();
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                Log.e("Network", "Failed");

            }
        });
    }

}

class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Genre> data;
    private int itemCount;

    GenreAdapter() {
        this(new ArrayList<Genre>());
    }

    GenreAdapter(@NonNull List<Genre> initialData) {
        data = initialData;
        itemCount = data.size();
    }

    void addItems(List<Genre> items) {
        int newItemsCount = items.size();
        data.addAll(items);
        notifyItemRangeInserted(itemCount, newItemsCount);
        itemCount += newItemsCount;
    }

    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GenreAdapter.ViewHolder holder, int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.genre)
        TextView textView;

        int genreId;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Genre genre) {
            textView.setText(genre.getName());
            genreId = genre.getId();
        }

        @OnClick(R.id.genre)
        void genreClicked() {
            Toast.makeText(textView.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}



