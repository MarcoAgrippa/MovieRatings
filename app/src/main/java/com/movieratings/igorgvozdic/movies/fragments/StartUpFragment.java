package com.movieratings.igorgvozdic.movies.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieratings.igorgvozdic.movies.Api;
import com.movieratings.igorgvozdic.movies.R;
import com.movieratings.igorgvozdic.movies.RetrofitUtils;
import com.movieratings.igorgvozdic.movies.model.FeedSeries;
import com.movieratings.igorgvozdic.movies.model.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StartUpFragment extends Fragment {

    private View myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.startup_fragment, container, false);

        Retrofit retrofit = RetrofitUtils.getRetrofit();

        Api api = retrofit.create(Api.class);

        Call<FeedSeries> call = api.getSeries(Api.TOP_RATED, 1);

        call.enqueue(new Callback<FeedSeries>() {
            @Override
            public void onResponse(Call<FeedSeries> call, Response<FeedSeries> response) {
                List<Series> series = response.body().getSeries();

                for (Series s : series) {
                    Log.d("Response", "onResponse: " + s.toString());
                }
            }

            @Override
            public void onFailure(Call<FeedSeries> call, Throwable t) {

            }
        });

        return myView;
    }
}
