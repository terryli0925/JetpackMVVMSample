package com.terry.jetpackmvvm.sample.di.component

import com.terry.jetpackmvvm.sample.di.scope.ActivityScope
import com.terry.jetpackmvvm.sample.ui.MainActivity
import com.terry.jetpackmvvm.sample.ui.SecondActivity
import com.terry.jetpackmvvm.sample.ui.repo.RepoFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: SecondActivity)
    fun inject(fragment: RepoFragment)

}