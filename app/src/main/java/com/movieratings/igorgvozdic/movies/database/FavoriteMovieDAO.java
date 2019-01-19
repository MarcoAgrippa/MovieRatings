package com.movieratings.igorgvozdic.movies.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteMovieDAO {

    @Insert
    void insert(FavoriteMovie favoriteMovie);

    @Delete
    void delete(FavoriteMovie favoriteMovie);

    @Query("DELETE FROM favorite_movie")
    void deleteAllFavoriteMovies();

    // Dodati LiveData
    @Query("SELECT * FROM favorite_movie ORDER BY popularity ASC")
    LiveData<List<FavoriteMovie>> getAllFavoriteMoviesLiveData();


}
