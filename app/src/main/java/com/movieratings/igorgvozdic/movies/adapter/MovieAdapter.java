package com.movieratings.igorgvozdic.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.movieratings.igorgvozdic.movies.R;
import com.movieratings.igorgvozdic.movies.fragments.MovieDetailsFragment;
import com.movieratings.igorgvozdic.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movies = new ArrayList<>();

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        if (movies != null) {
            final Movie currentMovie = movies.get(i);
            //Dodati slike u cartice       favoriteMovieViewHolder.imgFavoriteMovie.setImageDrawable(R.drawable.head);
            movieViewHolder.txtAverageScore.setText(String.valueOf(currentMovie.getVoteAverage()));
            Picasso.get().load(currentMovie.getPosterPath()).into(movieViewHolder.imgPoster);

            movieViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MovieDetailsFragment detailsFragment = new MovieDetailsFragment();

                    Bundle bundle = new Bundle();
                    bundle.putInt("vote_count", currentMovie.getVoteCount());
                    bundle.putInt("id", currentMovie.getId());
                    bundle.putDouble("vote_average", currentMovie.getVoteAverage());
                    bundle.putString("title", currentMovie.getTitle());
                    bundle.putDouble("popularity", currentMovie.getPopularity());
                    bundle.putString("poster_path", currentMovie.getPosterPath());
                    bundle.putString("language", currentMovie.getLanguage());
                    bundle.putBoolean("adult", currentMovie.isAdult());
                    bundle.putString("overview", currentMovie.getOverview());
                    bundle.putString("release_date", currentMovie.getReleaseDate());

                    detailsFragment.setArguments(bundle);
                    FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container, detailsFragment).addToBackStack(null).commit();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;
        TextView txtAverageScore;
        RelativeLayout parentLayout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgMoviePoster);
            txtAverageScore = itemView.findViewById(R.id.txtAverageScore);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
