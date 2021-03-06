package com.terry.jetpackmvvm.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseVMActivity<VB : ViewDataBinding, VM : ViewModel>(inflateMethod: (LayoutInflater) -> VB) :
        BaseActivity<VB>(inflateMethod) {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(getViewModelClass())
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        val type =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return type as Class<VM>
    }

}