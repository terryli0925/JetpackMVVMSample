package com.terry.jetpackmvvm.sample.di.component

import com.terry.jetpackmvvm.sample.di.scope.FragmentScope
import com.terry.jetpackmvvm.sample.ui.constraintlayoutsample.ConstraintLayoutSampleFragment
import com.terry.jetpackmvvm.sample.ui.repo.RepoFragment
import com.terry.jetpackmvvm.sample.ui.utiltest.UtilTestFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class])
interface FragmentComponent {

    fun inject(fragment: RepoFragment)
    fun inject(fragment: ConstraintLayoutSampleFragment)
    fun inject(fragment: UtilTestFragment)
}