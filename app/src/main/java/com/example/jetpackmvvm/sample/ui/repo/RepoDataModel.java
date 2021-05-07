package com.example.jetpackmvvm.sample.ui.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.jetpackmvvm.sample.data.api.GithubService;
import com.example.jetpackmvvm.sample.data.api.RetrofitManager;
import com.example.jetpackmvvm.sample.data.model.Repo;
import com.example.jetpackmvvm.sample.data.model.RepoSearchResponse;
import com.example.jetpackmvvm.sample.util.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDataModel {

    private GithubService githubService = RetrofitManager.getAPI();

    public MutableLiveData<Event<List<Repo>>> searchRepo(String query) {
        final MutableLiveData<Event<List<Repo>>> repos = new MutableLiveData<>();

        githubService.searchRepos(query).enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(Call<RepoSearchResponse> call, Response<RepoSearchResponse> response) {
                repos.setValue(new Event<>(response.body().getItems()));
            }

            @Override
            public void onFailure(Call<RepoSearchResponse> call, Throwable t) {

            }
        });
        return repos;
    }
}
