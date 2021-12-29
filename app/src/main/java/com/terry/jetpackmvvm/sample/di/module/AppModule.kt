package com.terry.jetpackmvvm.sample.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}