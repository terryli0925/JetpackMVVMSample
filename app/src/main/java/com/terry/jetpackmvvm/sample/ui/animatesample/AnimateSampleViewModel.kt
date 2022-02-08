package com.terry.jetpackmvvm.sample.ui.animatesample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimateSampleViewModel : ViewModel() {
    private val _text: MutableLiveData<String> = MutableLiveData()

    val text: LiveData<String>
        get() = _text

    init {
        _text.value = "This is slideshow fragment"
    }
}