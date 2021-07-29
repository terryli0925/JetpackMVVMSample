package com.example.jetpackmvvm.sample.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    var githubService: GithubService
        private set

    init {
        githubService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }
}