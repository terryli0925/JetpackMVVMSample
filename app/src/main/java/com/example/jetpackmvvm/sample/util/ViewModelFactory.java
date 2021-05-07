package com.example.jetpackmvvm.sample.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpackmvvm.sample.ui.repo.RepoDataModel;
import com.example.jetpackmvvm.sample.ui.repo.RepoViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RepoDataModel mDataModel;

    public ViewModelFactory(RepoDataModel dataModel) {
        this.mDataModel = dataModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RepoViewModel.class)) {
            return (T) new RepoViewModel(mDataModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
