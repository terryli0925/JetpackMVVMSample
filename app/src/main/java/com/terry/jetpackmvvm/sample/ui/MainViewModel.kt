package com.terry.jetpackmvvm.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terry.jetpackmvvm.sample.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor() : ViewModel() {
    private val _showBanner = MutableLiveData(true)

    val showBanner: LiveData<Boolean>
        get() = _showBanner

    fun setBannerState(isShow: Boolean) {
        _showBanner.postValue(isShow)
    }
}