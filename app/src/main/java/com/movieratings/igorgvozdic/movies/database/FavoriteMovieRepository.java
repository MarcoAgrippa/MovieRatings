package com.movieratings.igorgvozdic.movies.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FavoriteMovieRepository {

    private FavoriteMovieDAO favoriteMovieDAO;
    private LiveData<List<FavoriteMovie>> allFavoriteMoviesLiveData;

    public FavoriteMovieRepository(Application application) {
        FavoriteMovieDatabase database = FavoriteMovieDatabase.getDatabase(application);
        favoriteMovieDAO = database.favoriteMovieDAO();
        allFavoriteMoviesLiveData = favoriteMovieDAO.getAllFavoriteMoviesLiveData();
    }

    public void insert(FavoriteMovie favoriteMovie) {
        new InsertFavoriteMovieAsyncTask(favoriteMovieDAO).execute(favoriteMovie);
    }

    public void delete(FavoriteMovie favoriteMovie) {
        new DeleteFavoriteMovieAsyncTask(favoriteMovieDAO).execute(favoriteMovie);
    }

    public void deleteAll() {
        new DeleteAllFavoriteMovieAsyncTask(favoriteMovieDAO).execute();
    }

    public LiveData<List<FavoriteMovie>> getAllFavoriteMoviesLiveData() {
        return allFavoriteMoviesLiveData;
    }


    private static class InsertFavoriteMovieAsyncTask extends AsyncTask<FavoriteMovie, Void, Void> {

        private FavoriteMovieDAO favoriteMovieDAO;

        public InsertFavoriteMovieAsyncTask(FavoriteMovieDAO favoriteMovieDAO) {
            this.favoriteMovieDAO = favoriteMovieDAO;
        }

        @Override
        protected Void doInBackground(FavoriteMovie... favoriteMovies) {
            favoriteMovieDAO.insert(favoriteMovies[0]);
            return null;
        }
    }

    private static class DeleteFavoriteMovieAsyncTask extends AsyncTask<FavoriteMovie, Void, Void> {

        private FavoriteMovieDAO favoriteMovieDAO;

        public DeleteFavoriteMovieAsyncTask(FavoriteMovieDAO favoriteMovieDAO) {
            this.favoriteMovieDAO = favoriteMovieDAO;
        }

        @Override
        protected Void doInBackground(FavoriteMovie... favoriteMovies) {
            favoriteMovieDAO.delete(favoriteMovies[0]);
            return null;
        }
    }

    private static class DeleteAllFavoriteMovieAsyncTask extends AsyncTask<Void, Void, Void> {

        private FavoriteMovieDAO favoriteMovieDAO;

        public DeleteAllFavoriteMovieAsyncTask(FavoriteMovieDAO favoriteMovieDAO) {
            this.favoriteMovieDAO = favoriteMovieDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteMovieDAO.deleteAllFavoriteMovies();
            return null;
        }
    }


}
