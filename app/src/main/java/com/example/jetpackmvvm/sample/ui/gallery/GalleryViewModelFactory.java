package com.example.jetpackmvvm.sample.ui.gallery;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class GalleryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final GalleryDataModel dataModel;

    public GalleryViewModelFactory(GalleryDataModel dataModel) {
        this.dataModel = dataModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == GalleryViewModel.class) {
            return (T) new GalleryViewModel(dataModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
