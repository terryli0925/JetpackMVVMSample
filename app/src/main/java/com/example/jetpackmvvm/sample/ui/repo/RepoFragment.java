package com.example.jetpackmvvm.sample.ui.repo;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jetpackmvvm.sample.data.model.Repo;
import com.example.jetpackmvvm.sample.databinding.FragmentRepoBinding;
import com.example.jetpackmvvm.sample.util.Event;

import java.util.ArrayList;
import java.util.List;

public class RepoFragment extends Fragment {

    private FragmentRepoBinding mBinding;
    private RepoViewModel mViewModel;
    private RepoAdapter mRepoAdapter = new RepoAdapter(new ArrayList<Repo>());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentRepoBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(RepoViewModel.class);
        // 使用ViewModelFactory帶入construct參數
//        ViewModelFactory factory = new ViewModelFactory(new GalleryDataModel());
//        GalleryViewModel mViewModel = new ViewModelProvider(this, factory).get(GalleryViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);

        mBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSearch();
            }
        });
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mRepoAdapter);
        mViewModel.repos.observe(getViewLifecycleOwner(), new Observer<Event<List<Repo>>>() {
            @Override
            public void onChanged(Event<List<Repo>> event) {
                mRepoAdapter.swapItems(event.getContent());
            }
        });
        return mBinding.getRoot();
    }

    private void doSearch() {
        String query = mBinding.edtQuery.getText().toString();
        if (TextUtils.isEmpty(query)) {
            mRepoAdapter.clearItems();
            return;
        }
        mViewModel.searchRepo(query);
        dismissKeyboard();
    }

    private void dismissKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}