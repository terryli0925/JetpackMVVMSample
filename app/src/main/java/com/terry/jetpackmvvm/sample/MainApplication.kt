package com.terry.jetpackmvvm.sample

import android.app.Application
import com.terry.jetpackmvvm.sample.di.component.AppComponent
import com.terry.jetpackmvvm.sample.di.module.AppModule
import com.terry.jetpackmvvm.sample.di.component.DaggerAppComponent
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager

class MainApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        app = this
        intiDaggerComponent()
        ActivityManager.startWatcher(this)
    }

    private fun intiDaggerComponent() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        var app: MainApplication? = null
            private set
    }
}