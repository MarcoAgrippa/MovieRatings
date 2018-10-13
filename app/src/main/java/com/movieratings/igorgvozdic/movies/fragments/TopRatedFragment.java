package com.movieratings.igorgvozdic.movies.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

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

    private GridView gridView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.top_rated, container, false);

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

                for (Movie m : topRatedMovies){
                    Log.i(TAG, "Movie title: " + m.toString());
                }

                gridView = (GridView) myView.findViewById(R.id.top_rated_gridview);
                if (getActivity() != null){
                    MovieAdapter adapter = new MovieAdapter(getActivity(), topRatedMovies);
                    gridView.setAdapter(adapter);
                }


                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Movie movie = topRatedMovies.get(position);
                        MovieDetailsFragment detailsFragment = new MovieDetailsFragment();

                        Bundle bundle = new Bundle();
                        bundle.putInt("vote_count", movie.getVoteCount());
                        bundle.putInt("id", movie.getId());
                        bundle.putDouble("vote_average", movie.getVoteAverage());
                        bundle.putString("title", movie.getTitle());
                        bundle.putDouble("popularity", movie.getPopularity());
                        bundle.putString("poster_path", movie.getPosterPath());
                        bundle.putString("language", movie.getLanguage());
                        bundle.putBoolean("adult", movie.isAdult());
                        bundle.putString("overview", movie.getOverview());
                        bundle.putString("release_date", movie.getReleaseDate());

                        detailsFragment.setArguments(bundle);
                        FragmentManager manager = getFragmentManager();
                        manager.beginTransaction().replace(R.id.fragment_container, detailsFragment).addToBackStack(null).commit();

                    }
                });

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return myView;
    }
}
