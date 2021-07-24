package com.example.jetpackmvvm.sample.ui.switchsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpackmvvm.sample.databinding.FragmentSwitchSampleBinding;
import com.example.jetpackmvvm.sample.util.MainViewModel;

public class SwitchSampleFragment extends Fragment {

    private FragmentSwitchSampleBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentSwitchSampleBinding.inflate(inflater, container, false);
        mBinding.setLifecycleOwner(this);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mViewModel.getBannerState().observe(getViewLifecycleOwner(), state -> {
            mBinding.switch1.setChecked(state);
        });
        mBinding.switch1.setOnCheckedChangeListener((view, isChecked) -> {
            mViewModel.setBannerState(isChecked);
        });
        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}