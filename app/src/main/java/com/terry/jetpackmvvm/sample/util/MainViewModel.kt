package com.terry.jetpackmvvm.sample.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _showBanner = MutableLiveData(true)

    val showBanner: LiveData<Boolean>
        get() = _showBanner

    fun setBannerState(isShow: Boolean) {
        _showBanner.postValue(isShow)
    }
}