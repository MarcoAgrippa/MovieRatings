package com.movieratings.igorgvozdic.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieratings.igorgvozdic.movies.R;
import com.movieratings.igorgvozdic.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie>{


    public MovieAdapter(@NonNull Context context, @NonNull ArrayList<Movie> movies) {
        super(context, R.layout.movie_list_item,movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View gridItemView = convertView;

        if (convertView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
        }

        final Movie currentMovie = getItem(position);

        final ImageView imageView = gridItemView.findViewById(R.id.imgMoviePoster);
        Picasso.get().load(currentMovie.getPosterPath()).into(imageView);

        final TextView averageeScore = gridItemView.findViewById(R.id.txtAverageScore);
        averageeScore.setText(String.valueOf(currentMovie.getVoteAverage()));

        return gridItemView;
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return super.getItem(position);
    }
}
