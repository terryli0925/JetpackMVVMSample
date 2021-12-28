package com.terry.jetpackmvvm.sample.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.terry.jetpackmvvm.sample.MainApplication

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    protected lateinit var binding: T
    private var isFirstLoad = false

    override fun getContext(): Context? {
        return if (activity == null) {
            MainApplication.app
        } else activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        onLazyLoad()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = true
    }

    private fun onLazyLoad() {
        lazyLoadEvery()
        if (isFirstLoad) {
            lazyLoad()
            isFirstLoad = false
        }
    }

    protected open fun lazyLoad() {}

    protected open fun lazyLoadEvery() {}

    protected abstract fun getLayoutId(): Int

    protected abstract fun init()
}