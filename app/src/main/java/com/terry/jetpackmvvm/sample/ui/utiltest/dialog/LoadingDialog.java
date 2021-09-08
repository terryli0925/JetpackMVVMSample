package com.terry.jetpackmvvm.sample.ui.utiltest.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.terry.jetpackmvvm.sample.R;
import com.terry.jetpackmvvm.sample.databinding.DialogLoadingBinding;
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager;

/**
 * 接口共用loading dialog,避免多個api呼叫時產生多個dialog視窗
 */
public class LoadingDialog extends DialogFragment {
    DialogLoadingBinding binding;
    static LoadingDialog mDialog;
    static int loadNum = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_loading, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return binding.getRoot();
    }

    public static void build() {
        if (mDialog == null) {
            Activity activity = ActivityManager.getTagActivity();
            if (!activity.isFinishing()) {
                loadNum = 1;
                mDialog = new LoadingDialog();
                mDialog.show(((FragmentActivity)activity).getSupportFragmentManager(), "loading");
            }
        } else {
            loadNum ++;
        }
    }

    public static void dismissDialog() {
        loadNum --;
        if (loadNum <= 0) {
            if (mDialog != null) {
                mDialog.dismissAllowingStateLoss();
                mDialog = null;
            }
            loadNum = 0;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mDialog != null) {
            mDialog.dismissAllowingStateLoss();
            mDialog = null;
        }
        loadNum = 0;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

}