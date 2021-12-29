package com.terry.jetpackmvvm.sample.di.component

import com.terry.jetpackmvvm.sample.di.scope.ActivityScope
import com.terry.jetpackmvvm.sample.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}