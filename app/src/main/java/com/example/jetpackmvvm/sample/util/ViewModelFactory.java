package com.example.jetpackmvvm.sample.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpackmvvm.sample.ui.gallery.GalleryDataModel;
import com.example.jetpackmvvm.sample.ui.gallery.GalleryViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final GalleryDataModel mDataModel;

    public ViewModelFactory(GalleryDataModel dataModel) {
        this.mDataModel = dataModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GalleryViewModel.class)) {
            return (T) new GalleryViewModel(mDataModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
