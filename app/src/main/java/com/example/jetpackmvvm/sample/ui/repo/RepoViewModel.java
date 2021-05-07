package com.example.jetpackmvvm.sample.ui.repo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jetpackmvvm.sample.data.model.Repo;
import com.example.jetpackmvvm.sample.util.Event;

import java.util.List;

public class RepoViewModel extends ViewModel {

    public final MutableLiveData<Event<List<Repo>>> repos = new MutableLiveData<>();
    public final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private RepoDataModel repoDataModel;

    public RepoViewModel() {
        init(new RepoDataModel());
    }

    public RepoViewModel(RepoDataModel dataModel) {
        init(dataModel);
    }

    private void init(RepoDataModel dataModel) {
        repoDataModel = dataModel;
    }

    public void searchRepo(String query) {
        isLoading.setValue(true);
        repoDataModel.searchRepo(query, new RepoDataModel.onDataReadyCallback() {
            @Override
            public void onDataReady(List<Repo> data) {
                RepoViewModel.this.repos.setValue(new Event<>(data));
                isLoading.setValue(false);
            }
        });
    }
}