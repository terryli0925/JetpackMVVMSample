package com.example.jetpackmvvm.sample.ui.repo;

import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.jetpackmvvm.sample.data.model.Repo;
import com.example.jetpackmvvm.sample.util.AbsentLiveData;
import com.example.jetpackmvvm.sample.util.Event;

import java.util.List;

public class RepoViewModel extends ViewModel {

    public final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public LiveData<Event<List<Repo>>> repos;
    private final MutableLiveData<String> query = new MutableLiveData<>();
    private RepoDataModel repoDataModel;

    public RepoViewModel() {
        init(new RepoDataModel());
    }

    public RepoViewModel(RepoDataModel dataModel) {
        init(dataModel);
    }

    private void init(RepoDataModel dataModel) {
        repoDataModel = dataModel;
        repos = Transformations.switchMap(query, new Function<String, LiveData<Event<List<Repo>>>>() {
            @Override
            public LiveData<Event<List<Repo>>> apply(String input) {
                if (TextUtils.isEmpty(input)) {
                    return AbsentLiveData.create();
                } else {
                    return dataModel.searchRepo(input);
                }
            }
        });
    }

    public void searchRepo(String inputText) {
        query.setValue(inputText);
    }
}