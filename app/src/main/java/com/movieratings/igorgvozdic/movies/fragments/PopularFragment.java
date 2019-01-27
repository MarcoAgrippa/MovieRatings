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
import com.movieratings.igorgvozdic.movies.RetrofitUtils;
import com.movieratings.igorgvozdic.movies.adapter.MovieAdapter;
import com.movieratings.igorgvozdic.movies.model.FeedMovies;
import com.movieratings.igorgvozdic.movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PopularFragment extends Fragment {

    private static final String TAG = "PopularFragment";

    private ArrayList<Movie> popularMovies = new ArrayList<>();

    private GridLayoutManager gridLayoutManager;

    private RecyclerView recyclerView;

    private MovieAdapter movieAdapter;

    private int pageNumber = 1;

    private boolean isLoading = true;
    private int pastVisibleItems = 0;
    private int visibleItemCount = 0;
    private int totalItemCount = 0;
    private int previousTotal = 0;
    private int viewTreshold = 0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.mov_recyclerview, container, false);

        Retrofit retrofit = RetrofitUtils.getRetrofit();

        Api api = retrofit.create(Api.class);

        Call<FeedMovies> call = api.getMovies(Api.POPULAR, pageNumber);

        recyclerView = myView.findViewById(R.id.mov_recycler_view);

        call.enqueue(new Callback<FeedMovies>() {
            @Override
            public void onResponse(Call<FeedMovies> call, Response<FeedMovies> response) {
                Log.d(TAG, "onResponse: Sucessfull");

                popularMovies = response.body().getMovies();

                gridLayoutManager = new GridLayoutManager(getContext(), 3);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(gridLayoutManager);

                movieAdapter = new MovieAdapter(getContext(), popularMovies);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<FeedMovies> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }

                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + viewTreshold)) {
                        pageNumber++;
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });

        return myView;
    }

    private void performPagination() {

        Retrofit retrofit = RetrofitUtils.getRetrofit();

        Api api = retrofit.create(Api.class);

        Call<FeedMovies> call = api.getMovies(Api.POPULAR, pageNumber);

        call.enqueue(new Callback<FeedMovies>() {
            @Override
            public void onResponse(Call<FeedMovies> call, Response<FeedMovies> response) {
                Log.d(TAG, "onResponse: Sucessfull");

                List<Movie> popularMovies1 = response.body().getMovies();

                movieAdapter.addMovies(popularMovies1);

            }

            @Override
            public void onFailure(Call<FeedMovies> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
