package com.movieratings.igorgvozdic.movies;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieratings.igorgvozdic.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final List<Movie> moviesList;

    private static final String TAG = "MyMovieRecyclerViewAdap";

    public MyMovieRecyclerViewAdapter(Context context, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Called");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
         Log.d(TAG, "onBindViewHolder: Called");

         Movie movie= moviesList.get(position);
         holder.txtAverageScore.setText(String.valueOf(movie.getVoteAverage()));

         Picasso.get().load(Api.BASE_IMG_URL + Api.IMAGE_SIZE + movie.getPosterPath()).into(holder.imgMoviePoster);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: Called");
        return moviesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "ViewHolder";
        public final TextView txtAverageScore;
        public final ImageView imgMoviePoster;
        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            Log.d(TAG, "ViewHolder: Called");

            txtAverageScore = view.findViewById(R.id.txtAverageScore);
            imgMoviePoster = view.findViewById(R.id.imgMoviePoster);
        }

    }
}

