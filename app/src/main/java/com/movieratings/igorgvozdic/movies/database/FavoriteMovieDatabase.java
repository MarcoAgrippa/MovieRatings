package com.movieratings.igorgvozdic.movies.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = FavoriteMovie.class, version = 1)
public abstract class FavoriteMovieDatabase extends RoomDatabase {

    private static FavoriteMovieDatabase favoriteMovieDatabaseInstance;

    public static synchronized FavoriteMovieDatabase getDatabase(Context context) {
        if (favoriteMovieDatabaseInstance == null) {
            favoriteMovieDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteMovieDatabase.class, "favorite_movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return favoriteMovieDatabaseInstance;
    }

    public abstract FavoriteMovieDAO favoriteMovieDAO();
}
