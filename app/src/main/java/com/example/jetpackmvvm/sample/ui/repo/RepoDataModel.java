package com.example.jetpackmvvm.sample.ui.repo;

import com.example.jetpackmvvm.sample.data.api.GithubService;
import com.example.jetpackmvvm.sample.data.api.RetrofitManager;
import com.example.jetpackmvvm.sample.data.model.Repo;
import com.example.jetpackmvvm.sample.data.model.RepoSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDataModel {

    private GithubService githubService = RetrofitManager.getAPI();

    public void searchRepo(String query, final onDataReadyCallback callback) {
        githubService.searchRepos(query).enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(Call<RepoSearchResponse> call, Response<RepoSearchResponse> response) {
                callback.onDataReady(response.body().getItems());
            }

            @Override
            public void onFailure(Call<RepoSearchResponse> call, Throwable t) {

            }
        });

    }

    interface onDataReadyCallback {
        void onDataReady(List<Repo> data);
    }
}
