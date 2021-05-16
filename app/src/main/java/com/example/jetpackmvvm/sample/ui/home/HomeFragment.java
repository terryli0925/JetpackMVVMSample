package com.example.jetpackmvvm.sample.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpackmvvm.sample.R;
import com.example.jetpackmvvm.sample.databinding.FragmentHomeBinding;
import com.example.jetpackmvvm.sample.databinding.FragmentRepoBinding;
import com.example.jetpackmvvm.sample.util.DateUtil;
import com.example.jetpackmvvm.sample.util.MainViewModel;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private MainViewModel mViewModel;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mBinding.setLifecycleOwner(this);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mViewModel.getBannerState().observe(getViewLifecycleOwner(), state -> {
            Log.i("terry", "HomeFragment BannerState: " + state);
//            mBinding.textHome.setVisibility(state ? View.VISIBLE: View.GONE);
            mBinding.switch1.setChecked(state);
        });
        mBinding.switch1.setOnCheckedChangeListener((view, isChecked) -> {
            mViewModel.setBannerState(isChecked);
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mBinding.textHome.setText(s);
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initDateTest();

//        Calendar startCalendar = Calendar.getInstance();
//        startCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
//        startCalendar.set(Calendar.DATE, 14);
//        DateUtil.clearTime(startCalendar);

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
//        DateUtil.clearTime(calendar);
//
//        Log.i("terry", "HomeFragment calendar start: " + startCalendar.toString());
//        Log.i("terry", "HomeFragment calendar end: " + calendar.toString());
//
//        int days = DateUtil.getDaysBetween(startCalendar, calendar);
//        Log.i("terry", "HomeFragment calendar days: " + days);

//        Log.i("terry", "HomeFragment calendar locale " + calendar.toString());
//        clearTime(calendar);
////        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Log.i("terry", "HomeFragment calendar UTC: " + calendar.toString());
////        TimeUnit.MILLISECONDS.toDays(msDiff);
//
        showDialog();
    }

    private void initDateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.DATE, 14);
        DateUtil.clearTime(calendar);
        SharedPreferences shared = requireActivity().getPreferences(Context.MODE_PRIVATE);
        shared.edit().putLong("last_show_dialog", calendar.getTimeInMillis()).apply();
    }

    private void showDialog() {
        SharedPreferences shared = requireActivity().getPreferences(Context.MODE_PRIVATE);
        long last = shared.getLong("last_show_dialog", 0);
        Log.i("terry", "HomeFragment last: " + last);

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        DateUtil.clearTime(nowCalendar);
        long now = nowCalendar.getTimeInMillis();

        int days = DateUtil.getDaysBetween(last, now);
        Log.i("terry", "HomeFragment calendar days: " + days);
        if (last == 0 || days >= 1) {
            Log.i("terry", "HomeFragment show dialog");
            shared.edit().putLong("last_show_dialog", now).apply();
        } else {
            Log.i("terry", "HomeFragment not show dialog");
        }
    }
//
//    private void clearTime(final Calendar calendar) {
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//    }
//
//    private int getDaysBetween(final Calendar start, final Calendar end) {
//        return (int) (end.getTimeInMillis() - start.getTimeInMillis()) /  (1000 * 60 * 60 * 24);
//    }
}