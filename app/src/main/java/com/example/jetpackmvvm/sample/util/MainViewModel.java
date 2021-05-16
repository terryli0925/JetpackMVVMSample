package com.example.jetpackmvvm.sample.util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    private final MutableLiveData<Boolean> showBanner = new MutableLiveData<Boolean>(false);
    public void setBannerState(Boolean showBanner) {
        this.showBanner.setValue(showBanner);
    }
    public LiveData<Boolean> getBannerState() {
        return showBanner;
    }
}
