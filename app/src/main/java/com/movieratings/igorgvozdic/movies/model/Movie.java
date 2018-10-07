package com.movieratings.igorgvozdic.movies.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("vote_count")
    private int voteCount;
    private int id;
    @SerializedName("vote_average")
    private double voteAverage;
    private String title;
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String language;
    private boolean adult;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    public Movie(int voteCount, double voteAverage, String title, double popularity, String posterPath, String language, boolean adult, String overview, String releaseDate) {
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

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
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
        return "https://image.tmdb.org/t/p/w185" + posterPath;
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

    @Override
    public String toString() {
        return "Movie{" +
                "\nvoteCount=" + voteCount +
                "\n, id=" + id +
                "\n, voteAverage=" + voteAverage +
                "\n, title='" + title + '\'' +
                "\n, popularity=" + popularity +
                "\n, posterPath='" + posterPath + '\'' +
                "\n, language='" + language + '\'' +
                "\n, adult=" + adult +
                "\n, overview='" + overview + '\'' +
                "\n, releaseDate='" + releaseDate + '\'' +
                '}';
    }
}

//    vote_count: 1959,
//    id: 348350,
//    video: false,
//    vote_average: 6.7,
//    title: "Solo: A Star Wars Story",
//    popularity: 201.852,
//    poster_path: "/3IGbjc5ZC5yxim5W0sFING2kdcz.jpg",
//    original_language: "en",
//    original_title: "Solo: A Star Wars Story",
//            -genre_ids: (3)[
//            28,
//            12,
//            878
//            ],
//    backdrop_path: "/96B1qMN9RxrAFu6uikwFhQ6N6J9.jpg",
//    adult: false,
//    overview: "Through a series of daring escapades deep within a dark and dangerous criminal underworld, Han Solo meets his mighty future copilot Chewbacca and encounters the notorious gambler Lando Calrissian.",
//    release_date: "2018-05-15"