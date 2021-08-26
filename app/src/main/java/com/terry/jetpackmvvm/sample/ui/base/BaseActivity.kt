package com.terry.jetpackmvvm.sample.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected var bundle: Bundle? = null
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = intent.extras
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        init()
    }

    /**
     * Set layout id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * Initial
     */
    protected abstract fun init()
}