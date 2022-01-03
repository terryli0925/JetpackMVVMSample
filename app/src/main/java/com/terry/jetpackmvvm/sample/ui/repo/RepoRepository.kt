package com.terry.jetpackmvvm.sample.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.terry.jetpackmvvm.sample.data.LoadingCallback
import com.terry.jetpackmvvm.sample.data.api.GithubService
import com.terry.jetpackmvvm.sample.data.api.RetrofitManager
import com.terry.jetpackmvvm.sample.data.model.Repo
import com.terry.jetpackmvvm.sample.data.model.RepoSearchResponse
import com.terry.jetpackmvvm.sample.util.Event
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoRepository @Inject constructor(private val githubService: GithubService) {

    fun searchRepo(query: String): LiveData<Event<List<Repo>>> {
        val repos = MutableLiveData<Event<List<Repo>>>()
        githubService.searchRepos(query).enqueue(object : LoadingCallback<RepoSearchResponse>() {
            override fun onResponse(
                call: Call<RepoSearchResponse>,
                response: Response<RepoSearchResponse>
            ) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        repos.value = Event(body.items)
                    } else {
                        repos.value = Event(mutableListOf())
                    }
                }
            }

            override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                super.onFailure(call, t)
            }
        })
        return repos
    }
}