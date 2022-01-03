package com.terry.jetpackmvvm.sample.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.terry.jetpackmvvm.sample.ui.repo.RepoRepository
import com.terry.jetpackmvvm.sample.ui.repo.RepoViewModel

class ViewModelFactory(private val repoRepository: RepoRepository) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return RepoViewModel(repoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}

/**
 * 直接帶入ViewModel，不需要再綁死dataModel
 */
class ViewModelFactory2 <T> (val creator: () -> T): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}