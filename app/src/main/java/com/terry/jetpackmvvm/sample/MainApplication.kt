package com.terry.jetpackmvvm.sample

import android.app.Application
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager

class MainApplication: Application() {

    private lateinit var _app: MainApplication

    val app: MainApplication
        get() = _app

    override fun onCreate() {
        super.onCreate()
        _app = this
        ActivityManager.startWatcher(this)
    }
}