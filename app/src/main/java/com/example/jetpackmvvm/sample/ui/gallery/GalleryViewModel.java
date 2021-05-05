package com.example.jetpackmvvm.sample.ui.gallery;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private GalleryDataModel galleryDataModel = new GalleryDataModel();
    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void updateText(String text) {
        galleryDataModel.retrieveData(new GalleryDataModel.onDataReadyCallback() {
            @Override
            public void onDataReady(String data) {
                mText.setValue(text);
            }
        });
    }
}