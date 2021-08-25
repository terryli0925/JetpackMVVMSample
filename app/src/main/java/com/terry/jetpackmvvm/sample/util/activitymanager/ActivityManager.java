package com.terry.jetpackmvvm.sample.util.activitymanager;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ActivityManager {
    private static LinkedList<Activity> activityList;
    private static Set<Activity> acaActivitys;
    public static ActivityLifecycleCallbacks callbacks;
    private static Application app;
    private static LinkedList<Activity> dymanicActivitys;

    /**
     * 需在Application中注册
     *
     * @param application
     */
    public static void startWatcher(Application application) {
        if (callbacks != null || activityList != null || acaActivitys != null) return;
        activityList = new LinkedList<>();
        acaActivitys = new HashSet<>();
        dymanicActivitys = new LinkedList<>();
        app = application;
        callbacks = new ActivityLifecycleCallbacks(activityList, acaActivitys, dymanicActivitys);
        application.registerActivityLifecycleCallbacks(callbacks);
    }

    public static void stopWatcher() {
        if (app == null || callbacks == null) return;
        app.unregisterActivityLifecycleCallbacks(callbacks);
    }

    /**
     * App是否在前台
     *
     * @return
     */
    public static boolean appIsFont() {
        if (acaActivitys == null)
            throw new RuntimeException("you should invoke startWatcher method first");
        return acaActivitys.size() != 0;
    }

    /**
     * 退出程序
     */
    public static void exitApp() {
        if (activityList == null)
            throw new RuntimeException("you should invoke startWatcher method first");
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    public static void toActivity(Class<? extends Activity> cls) {
        for (int i = activityList.size() - 1; i >= 0; i--) {
            Activity activity = activityList.get(i);
            if (activity.getClass().equals(cls)) return;
            activity.finish();
        }
    }

    public static Activity getTagActivity() {
        if (callbacks != null)
            return callbacks.tagActivity;
        return null;
    }

    public static List<Activity> getStackActivitys() {
        return activityList;
    }

    public static List<Activity> getDymanicActivitys() {
        return dymanicActivitys;
    }
}
