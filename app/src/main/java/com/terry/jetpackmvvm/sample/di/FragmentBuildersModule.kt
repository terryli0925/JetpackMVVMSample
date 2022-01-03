package com.terry.jetpackmvvm.sample.di

import com.terry.jetpackmvvm.sample.ui.constraintlayoutsample.ConstraintLayoutSampleFragment
import com.terry.jetpackmvvm.sample.ui.repo.RepoFragment
import com.terry.jetpackmvvm.sample.ui.switchsample.SwitchSampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment

    @ContributesAndroidInjector
    abstract fun contributeConstraintLayoutSampleFragment(): ConstraintLayoutSampleFragment
}
