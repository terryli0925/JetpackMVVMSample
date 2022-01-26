package com.terry.jetpackmvvm.sample.util.activitymanager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.Set;

import timber.log.Timber;

public class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "ActivityLifecycle";

    private LinkedList<Activity> activityList;
    private Set<Activity> acaActivitys;
    private boolean isAppBack;
    public Activity tagActivity;
    private OnAppStatusListener onAppStatusListener;
    private LinkedList<Activity> dymanicActivitys;

    public ActivityLifecycleCallbacks(LinkedList<Activity> activityList, Set<Activity> acaActivitys, LinkedList<Activity> dymanicActivitys) {
        this.activityList = activityList;
        this.acaActivitys = acaActivitys;
        this.dymanicActivitys = dymanicActivitys;
    }

    public void setOnAppStatusListener(OnAppStatusListener onAppStatusListener) {
        this.onAppStatusListener = onAppStatusListener;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        log(activity, "onActivityCreated");
        activityList.add(activity);
        tagActivity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        log(activity, "onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        log(activity, "onActivityResumed");

        tagActivity = activity;
        acaActivitys.add(activity);
        if (isAppBack) {
            isAppBack = false;
            if (onAppStatusListener != null) {
                onAppStatusListener.onFront();
            }
        }
        dymanicActivitys.add(activity);
    }


    @Override
    public void onActivityPaused(Activity activity) {
        log(activity, "onActivityPaused");

        if (dymanicActivitys.contains(activity)) {
            dymanicActivitys.remove(activity);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        log(activity, "onActivityStopped");

        if (acaActivitys.contains(activity)) {
            acaActivitys.remove(activity);
        }
        if (acaActivitys.size() == 0) {
            if (onAppStatusListener != null) {
                onAppStatusListener.onBack();
            }
            isAppBack = true;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        log(activity, "onActivityDestroyed");
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    private void log(Activity activity, String state) {
        Timber.d(activity.getClass().getSimpleName() + " " + state);
    }
}

