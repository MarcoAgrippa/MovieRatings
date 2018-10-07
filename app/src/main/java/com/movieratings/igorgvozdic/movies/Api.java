package com.movieratings.igorgvozdic.movies;

import com.movieratings.igorgvozdic.movies.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Api {

//    http://api.themoviedb.org/3/movie/popular?api_key=9a3b1cdef9be66e1af71e4637daabe22
    String BASE_URL = "http://api.themoviedb.org/3/movie/";
    String BASE_IMG_URL = "https://image.tmdb.org/t/p/";
    String API_KEY = "?api_key=9a3b1cdef9be66e1af71e4637daabe22";
    String IMAGE_SIZE = "w1852";

    @Headers("Content-Type: application/json")
    @GET("{category}?api_key=9a3b1cdef9be66e1af71e4637daabe22")
    Call<Feed> getMovies(@Path("category") String category);
}