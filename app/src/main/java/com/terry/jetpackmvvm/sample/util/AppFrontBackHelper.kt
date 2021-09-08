package com.terry.jetpackmvvm.sample.util

import android.app.Activity

import android.os.Bundle

import android.app.Application

class AppFrontBackHelper {
    private var onAppStatusListener: AppFrontBackHelper.OnAppStatusListener? = null

    /**
     * 最后一次可见的Activity
     * 用于比对Activity，这样可以排除启动应用时的这种特殊情况，
     * 如果启动应用时也需要锁屏等操作，请在启动页里进行操作。
     */
    private var lastVisibleActivity: Activity? = null

    /**
     * 注册状态监听，仅在Application中使用
     * @param application
     * @param listener
     */
    fun register(application: Application, listener: AppFrontBackHelper.OnAppStatusListener?) {
        onAppStatusListener = listener
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    fun unRegister(application: Application){
        application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private val activityLifecycleCallbacks: Application.ActivityLifecycleCallbacks =
        object : Application.ActivityLifecycleCallbacks {
            //打开的Activity数量统计
            private var activityStartCount = 0
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {
                activityStartCount++
            }

            override fun onActivityResumed(activity: Activity) {
                //数值从0变到1说明是从后台切到前台
                if (activityStartCount == 1) {
                    if (lastVisibleActivity === activity) {
                        //从后台切到前台
                        onAppStatusListener?.onFront(false, activity)
                    } else {
                        onAppStatusListener?.onFront(true, activity)
                    }
                }
                lastVisibleActivity = activity
            }

            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {
                activityStartCount--
                //数值从1到0说明是从前台切到后台
                if (activityStartCount == 0) {
                    //从前台切到后台
                    onAppStatusListener?.onBack()
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        }

    interface OnAppStatusListener {
        fun onFront(isAppFirstStart: Boolean, curActivity: Activity?)
        fun onBack()
    }
}