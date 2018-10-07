package com.movieratings.igorgvozdic.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieratings.igorgvozdic.movies.model.Feed;
import com.movieratings.igorgvozdic.movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PopularMoviesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;


    private static final String TAG = "PopularMoviesFragment";

    private  ArrayList<Movie> movies = new ArrayList<>();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PopularMoviesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PopularMoviesFragment newInstance(int columnCount) {
        PopularMoviesFragment fragment = new PopularMoviesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.i(TAG, "onCreate: Called");
//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//            Log.d(TAG, "onCreate: mColumnCount = " + mColumnCount);
//        }
//
//        movies = getMovieData();
//
//
//}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Called");
        View view = inflater.inflate(R.layout.testgrid, container, false);

//        movies = getMovieData();
//        GridView gridView = view.findViewById(R.id.gridview);
//        TestAdapter adapter = new TestAdapter(this.getContext(), movies);
//        gridView.setAdapter(adapter);


//        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), mColumnCount);
//        recyclerView.setLayoutManager(layoutManager);
//
//        movies = getMovieData();
//        MyMovieRecyclerViewAdapter adapter = new MyMovieRecyclerViewAdapter(getContext(), movies);
//        recyclerView.setAdapter(adapter);

//        // Set the adapter
////        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//
//            if (recyclerView != null){
//                Log.d(TAG, "RecyclerView: WORKS ");
//            }
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new MyMovieRecyclerViewAdapter(movies, mListener));
//        }
        return view;
    }


    private ArrayList<Movie> getMovieData(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Feed> call =  api.getMovies("popular");

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Sucesfull");

                List<Movie> movies = response.body().getMovie();

                if (movies != null){
                    for (Movie m : movies){
                        Log.d(TAG, "onResponse: " + m.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return movies;
    }
}
