package com.movieratings.igorgvozdic.movies.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieratings.igorgvozdic.movies.Api;
import com.movieratings.igorgvozdic.movies.R;
import com.movieratings.igorgvozdic.movies.adapter.MovieAdapter;
import com.movieratings.igorgvozdic.movies.model.Feed;
import com.movieratings.igorgvozdic.movies.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopRatedFragment  extends Fragment {

    private View myView;

    private static final String TAG = "TopRatedFragment";

    private ArrayList<Movie> topRatedMovies = new ArrayList<>();

    private GridLayoutManager gridLayoutManager;

    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mov_recyclerview, container, false);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Feed> call = api.getMovies("top_rated");

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Sucesfull");

                topRatedMovies = response.body().getMovie();

                recyclerView = myView.findViewById(R.id.mov_recycler_view);
                gridLayoutManager = new GridLayoutManager(getContext(), 3);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(gridLayoutManager);

                MovieAdapter movieAdapter = new MovieAdapter(getContext(), topRatedMovies);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return myView;
    }
}
