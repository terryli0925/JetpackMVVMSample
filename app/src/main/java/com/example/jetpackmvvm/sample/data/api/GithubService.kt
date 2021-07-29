package com.example.jetpackmvvm.sample.data.api

import retrofit2.http.GET
import com.example.jetpackmvvm.sample.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): Call<RepoSearchResponse>
}