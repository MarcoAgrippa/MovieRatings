package com.movieratings.igorgvozdic.movies.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.movieratings.igorgvozdic.movies.Api;
import com.movieratings.igorgvozdic.movies.RetrofitUtils;
import com.movieratings.igorgvozdic.movies.model.FeedSeries;
import com.movieratings.igorgvozdic.movies.model.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SeriesRepository {

    private Api api;

    public SeriesRepository() {

        Retrofit retrofit = RetrofitUtils.getRetrofit();
        api = retrofit.create(Api.class);
    }

    public static SeriesRepository getInstance() {

        return SingletonHelper.INSTANCE;

    }

    public LiveData<List<Series>> getSeries(String category, int pageNumber) {

        final MutableLiveData<List<Series>> data = new MutableLiveData<>();
        Call<FeedSeries> call = api.getSeries(category, pageNumber);

        call.enqueue(new Callback<FeedSeries>() {
            @Override
            public void onResponse(Call<FeedSeries> call, Response<FeedSeries> response) {

                if (response.isSuccessful()) {
                    List<Series> series = response.body().getSeries();
                    data.setValue(series);
                }
            }

            @Override
            public void onFailure(Call<FeedSeries> call, Throwable t) {

            }
        });
        return data;
    }

    private static class SingletonHelper {

        private static final SeriesRepository INSTANCE = new SeriesRepository();
    }


}
