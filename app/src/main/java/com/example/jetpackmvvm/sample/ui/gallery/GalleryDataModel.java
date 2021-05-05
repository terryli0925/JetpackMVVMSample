package com.example.jetpackmvvm.sample.ui.gallery;

import android.os.Handler;

public class GalleryDataModel {

    public void retrieveData(final onDataReadyCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onDataReady("Refresh done");
            }
        }, 1500);
    }

    interface onDataReadyCallback {
        void onDataReady(String data);
    }
}
