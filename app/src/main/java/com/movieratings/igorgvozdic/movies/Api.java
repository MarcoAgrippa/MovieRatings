package com.movieratings.igorgvozdic.movies;

import com.movieratings.igorgvozdic.movies.model.FeedMovies;
import com.movieratings.igorgvozdic.movies.model.FeedSeries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    //    http://api.themoviedb.org/3/movie/popular&api_key=9a3b1cdef9be66e1af71e4637daabe22?page=2
    String BASE_URL = "http://api.themoviedb.org/3/";
    String MOVIE = "movie/";
    String SERIES = "tv/";

    String POPULAR = "popular";
    String TOP_RATED = "top_rated";
    String UPCOMING = "upcoming";

    String BASE_IMG_URL = "https://image.tmdb.org/t/p/";
    String API_KEY = "?api_key=9a3b1cdef9be66e1af71e4637daabe22";
    String IMAGE_SIZE = "w185";

    @Headers("Content-Type: application/json")
    @GET(MOVIE + "{category}?api_key=9a3b1cdef9be66e1af71e4637daabe22")
    Call<FeedMovies> getMovies(@Path("category") String category, @Query("page") int page);

    @Headers("Content-Type: application/json")
    @GET(SERIES + "{category}" + API_KEY)
    Call<FeedSeries> getSeries(@Path("category") String category, @Query("page") int page);
}
