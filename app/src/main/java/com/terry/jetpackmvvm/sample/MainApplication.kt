package com.terry.jetpackmvvm.sample

import android.app.Application
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        ActivityManager.startWatcher(this)
    }

    companion object {
        var app: MainApplication? = null
            private set
    }
}