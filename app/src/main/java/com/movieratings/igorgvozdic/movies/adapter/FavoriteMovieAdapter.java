package com.movieratings.igorgvozdic.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieratings.igorgvozdic.movies.R;
import com.movieratings.igorgvozdic.movies.database.FavoriteMovie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder> {

    private List<FavoriteMovie> favoriteMovies = new ArrayList<>();
    private Context mContext;

    public FavoriteMovieAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_movie_item, parent, false);
        FavoriteMovieViewHolder viewHolder = new FavoriteMovieViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder favoriteMovieViewHolder, int i) {

        if (favoriteMovies != null) {
            FavoriteMovie currentFavoriteMovie = favoriteMovies.get(i);
            //Dodati slike u cartice       favoriteMovieViewHolder.imgFavoriteMovie.setImageDrawable(R.drawable.head);
            favoriteMovieViewHolder.txtFavoriteMovieAverageScore.setText(String.valueOf(currentFavoriteMovie.getVoteAverage()));
            favoriteMovieViewHolder.txtFavoriteMovieTitle.setText(currentFavoriteMovie.getTitle());

        }
    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size();
    }

    public void setFavoriteMovies(List<FavoriteMovie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
        notifyDataSetChanged();
    }

    public FavoriteMovie getFavoriteMovieAt(int position) {
        return favoriteMovies.get(position);
    }

    public void sortByVoteAverage() {
        for (int i = 0; i < favoriteMovies.size(); i++) {
            //implementirati method za sortiranje liste
        }
    }

    class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFavoriteMovie;
        private TextView txtFavoriteMovieTitle;
        private TextView txtFavoriteMovieAverageScore;

        public FavoriteMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavoriteMovie = itemView.findViewById(R.id.img_favorite_movie);
            txtFavoriteMovieTitle = itemView.findViewById(R.id.txt_favorite_movie_title);
            txtFavoriteMovieAverageScore = itemView.findViewById(R.id.txt_favorite_movie_rating);
        }
    }
}
