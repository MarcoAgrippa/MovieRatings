package com.movieratings.igorgvozdic.movies.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoriteMovieViewModel extends AndroidViewModel {

    private FavoriteMovieRepository repository;
    private LiveData<List<FavoriteMovie>> allFavoriteMoviesLiveData;

    public FavoriteMovieViewModel(@NonNull Application application) {
        super(application);
        this.repository = new FavoriteMovieRepository(application);
        this.allFavoriteMoviesLiveData = repository.getAllFavoriteMoviesLiveData();
    }

    public void insert(FavoriteMovie favoriteMovie) {
        repository.insert(favoriteMovie);
    }

    public void delete(FavoriteMovie favoriteMovie) {
        repository.delete(favoriteMovie);
    }

    public void deleteAllFavoriteMovies() {
        repository.deleteAll();
    }

    public LiveData<List<FavoriteMovie>> getAllFavoriteMoviesLiveData() {
        return allFavoriteMoviesLiveData;
    }


}
