package com.example.jetpackmvvm.sample.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.jetpackmvvm.sample.ui.repo.RepoDataModel
import com.example.jetpackmvvm.sample.ui.repo.RepoViewModel

class ViewModelFactory(private val dataModel: RepoDataModel) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return RepoViewModel(dataModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}