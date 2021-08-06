package com.terry.jetpackmvvm.sample.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.terry.jetpackmvvm.sample.data.api.RetrofitManager
import com.terry.jetpackmvvm.sample.data.model.Repo
import com.terry.jetpackmvvm.sample.data.model.RepoSearchResponse
import com.terry.jetpackmvvm.sample.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoDataModel {
    private val githubService = RetrofitManager.githubService

    fun searchRepo(query: String): LiveData<Event<List<Repo>>> {
        val repos = MutableLiveData<Event<List<Repo>>>()
        githubService.searchRepos(query).enqueue(object : Callback<RepoSearchResponse> {
            override fun onResponse(call: Call<RepoSearchResponse>, response: Response<RepoSearchResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        repos.value = Event(body.items)
                    } else {
                        repos.value = Event(mutableListOf())
                    }
                }
            }

            override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {}
        })
        return repos
    }
}