package com.terry.jetpackmvvm.sample

import android.app.Activity
import android.app.Application
import com.terry.jetpackmvvm.sample.di.AppInjector
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        app = this
        AppInjector.init(this)
        ActivityManager.startWatcher(this)
    }

    companion object {
        var app: MainApplication? = null
            private set
    }

}