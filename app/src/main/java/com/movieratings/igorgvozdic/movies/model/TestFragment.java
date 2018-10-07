package com.movieratings.igorgvozdic.movies.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.movieratings.igorgvozdic.movies.Api;
import com.movieratings.igorgvozdic.movies.R;
import com.movieratings.igorgvozdic.movies.TestAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestFragment extends Fragment {

    private View myView;

    private static final String TAG = "TestFragment";

    private ArrayList<Movie> movies = new ArrayList<>();

    Movie movie1 = new Movie(0,5.5,"intruders",2.4,null,null, false, null,null);
    Movie movie2 = new Movie(0,5.5,"intruders",2.4,null,null, false, null,null);
    Movie movie3 = new Movie(0,5.5,"intruders",2.4,null,null, false, null,null);
    Movie movie4 = new Movie(0,5.5,"intruders",2.4,null,null, false, null,null);
    Movie movie5 = new Movie(0,5.5,"intruders",2.4,null,null, false, null,null);
    Movie movie6 = new Movie(0,5.5,"intruders",2.4,null,null, false, null,null);
    Movie movie7= new Movie(0,5.5,"intruders",2.4,null,null, false, null,null);
    private GridView gridView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.testgrid, container, false);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Feed> call = api.getMovies("popular");



        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Sucesfull");


                movies = response.body().getMovie();

                if (movies != null) {
                    for (Movie m : movies) {
                        Log.d(TAG, "onResponse: " + m.getPosterPath());
                    }
                }

                gridView = (GridView) myView.findViewById(R.id.gridview);
                TestAdapter adapter = new TestAdapter(getActivity(), movies);
                gridView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        for (Movie m : movies){
            Log.i(TAG, "Movie title: " + m.getTitle());
            Log.i(TAG, "Movie average: " + m.getVoteAverage());
        }

        return myView;


    }

}

