package com.terry.jetpackmvvm.sample.util.activitymanager;

public interface OnAppStatusListener {
    /**
     * Back to resume
     */
    void onFront();

    /**
     * Enter suspend
     */
    void onBack();
}

