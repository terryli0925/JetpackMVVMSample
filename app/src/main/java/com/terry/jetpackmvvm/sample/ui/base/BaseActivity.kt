package com.terry.jetpackmvvm.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.terry.jetpackmvvm.sample.di.Injectable
import dagger.android.support.HasSupportFragmentInjector

abstract class BaseActivity<VB : ViewDataBinding>(private val inflateMethod: (LayoutInflater) -> VB) :
        AppCompatActivity(), Injectable, HasSupportFragmentInjector {

    protected var bundle: Bundle? = null
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = intent.extras
        _binding = inflateMethod.invoke(layoutInflater)
        setContentView(binding.root)
        init()
    }

    /**
     * Initial
     */
    protected abstract fun init()
}