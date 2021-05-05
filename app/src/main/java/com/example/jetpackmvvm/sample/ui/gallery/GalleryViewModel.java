package com.example.jetpackmvvm.sample.ui.gallery;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    public final MutableLiveData<String> text = new MutableLiveData<>();
    public final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private GalleryDataModel galleryDataModel;

    public GalleryViewModel() {
        init(new GalleryDataModel());
    }

    public GalleryViewModel(GalleryDataModel dataModel) {
        init(dataModel);
    }

    private void init(GalleryDataModel dataModel) {
        galleryDataModel = dataModel;
//        text.setValue("This is gallery fragment");
    }

    public void refresh() {
        isLoading.setValue(true);
        galleryDataModel.retrieveData(new GalleryDataModel.onDataReadyCallback() {
            @Override
            public void onDataReady(String data) {
                GalleryViewModel.this.text.setValue(data);
                isLoading.setValue(false);
            }
        });
    }
}