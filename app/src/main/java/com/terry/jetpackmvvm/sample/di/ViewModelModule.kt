package com.terry.jetpackmvvm.sample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.jetpackmvvm.sample.ui.repo.RepoViewModel
import com.terry.jetpackmvvm.sample.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
