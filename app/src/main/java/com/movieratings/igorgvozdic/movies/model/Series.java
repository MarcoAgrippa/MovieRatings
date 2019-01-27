package com.movieratings.igorgvozdic.movies.model;

import com.google.gson.annotations.SerializedName;
import com.movieratings.igorgvozdic.movies.Api;

import java.io.Serializable;

public class Series implements Serializable {

    @SerializedName("original_name")
    private String name;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("first_air_date")
    private String firstAired;

    @SerializedName("original_language")
    private String language;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    public Series(String name, double popularity, int voteCount, String firstAired, String language,
                  int id, double voteAverage, String overview, String posterPath) {
        this.name = name;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.firstAired = firstAired;
        this.language = language;
        this.id = id;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public String getName() {
        return name;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public String getLanguage() {
        return language;
    }

    public int getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return Api.BASE_IMG_URL + Api.IMAGE_SIZE + posterPath;
    }

    @Override
    public String toString() {
        return "Series{" +
                "\nname='" + name + '\'' +
                ",\npopularity=" + popularity +
                ",\n voteCount=" + voteCount +
                ",\nfirstAired='" + firstAired + '\'' +
                ",\nlanguage='" + language + '\'' +
                ",\nid=" + id +
                ",\nvoteAverage=" + voteAverage +
                ",\noverview='" + overview + '\'' +
                ",\nposterPath='" + posterPath + '\'' +
                '}';
    }
}
