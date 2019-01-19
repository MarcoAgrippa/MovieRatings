package com.movieratings.igorgvozdic.movies.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.movieratings.igorgvozdic.movies.R;
import com.movieratings.igorgvozdic.movies.adapter.FavoriteMovieAdapter;
import com.movieratings.igorgvozdic.movies.database.FavoriteMovie;
import com.movieratings.igorgvozdic.movies.database.FavoriteMovieViewModel;

import java.util.List;

public class FavoriteMoviesFragment extends Fragment {

    private FavoriteMovieViewModel viewModel;
    private FavoriteMovieAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favorite_movie_recyclerview, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new FavoriteMovieAdapter(getContext());
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(FavoriteMovieViewModel.class);
        viewModel.getAllFavoriteMoviesLiveData().observe(this, new Observer<List<FavoriteMovie>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteMovie> favoriteMovies) {
                //update RecyclerView
                adapter.setFavoriteMovies(favoriteMovies);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                viewModel.delete(adapter.getFavoriteMovieAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Removed from Favorite Movies", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.favorite_movie_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.delete_all_favorite_movies:
                //U slucaju da je lista prazna ispisuje poruku da nema filmova u listi omiljenih (favorite movies)
                if (adapter.getItemCount() == 0) {
                    Toast.makeText(getContext(), "List is empty, nothing to delete", Toast.LENGTH_LONG).show();
                    return true;
                } else {
                    // Ukoliko ima, brise sve omiljene filmove (favorite movies) i ispisuje poruku
                    viewModel.deleteAllFavoriteMovies();
                    Toast.makeText(getContext(), "List of favorite movies is cleared", Toast.LENGTH_LONG).show();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
