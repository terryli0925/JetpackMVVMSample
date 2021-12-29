package com.terry.jetpackmvvm.sample.di.component

import android.content.Context
import com.terry.jetpackmvvm.sample.data.api.GithubService
import com.terry.jetpackmvvm.sample.di.module.ApiModule
import com.terry.jetpackmvvm.sample.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun getContext(): Context
    fun getGithubService(): GithubService
}