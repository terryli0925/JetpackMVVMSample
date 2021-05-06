package com.example.jetpackmvvm.sample.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpackmvvm.sample.databinding.FragmentGalleryBinding;
import com.example.jetpackmvvm.sample.util.ViewModelFactory;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding mBinding;
    private GalleryViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentGalleryBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        // 使用ViewModelFactory帶入construct參數
//        ViewModelFactory factory = new ViewModelFactory(new GalleryDataModel());
//        GalleryViewModel mViewModel = new ViewModelProvider(this, factory).get(GalleryViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        mBinding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.refresh();
            }
        });
        mViewModel.text.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                mBinding.textGallery.setText(data);
                Toast.makeText(getActivity(), "更新完成", Toast.LENGTH_SHORT).show();
            }
        });
        return mBinding.getRoot();
    }
}