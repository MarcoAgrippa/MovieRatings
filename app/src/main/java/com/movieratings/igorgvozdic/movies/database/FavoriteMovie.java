package com.movieratings.igorgvozdic.movies.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.movieratings.igorgvozdic.movies.model.Movie;

@Entity(tableName = "favorite_movie")
public class FavoriteMovie {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "vote_count")
    private int voteCount;

    @ColumnInfo(name = "vote_average")
    private double voteAverage;

    private String title;
    private double popularity;
    private String posterPath;
    private String language;
    private boolean adult;
    private String overview;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    public FavoriteMovie(int voteCount, double voteAverage, String title,
                         double popularity, String posterPath, String language, boolean adult,
                         String overview, String releaseDate) {
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.language = language;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public FavoriteMovie(Movie movie) {
        this.voteCount = movie.getVoteCount();
        this.title = movie.getTitle();
        this.voteAverage = movie.getVoteAverage();
        this.popularity = movie.getPopularity();
        this.posterPath = movie.getPosterPath();
        this.language = movie.getLanguage();
        this.adult = movie.isAdult();
        this.overview = movie.getOverview();
        this.releaseDate = movie.getReleaseDate();

    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}
