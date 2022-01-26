package com.terry.jetpackmvvm.sample

import android.app.Application
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager
import timber.log.Timber
import timber.log.Timber.DebugTree


class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        ActivityManager.startWatcher(this)
    }

    companion object {
        var app: MainApplication? = null
            private set
    }
}