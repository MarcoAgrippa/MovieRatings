package com.movieratings.igorgvozdic.movies.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SeriesViewModel extends ViewModel {

    private static final MutableLiveData MUTABLE_LIVE_DATA = new MutableLiveData();

    {
        MUTABLE_LIVE_DATA.setValue(null);
    }

}
