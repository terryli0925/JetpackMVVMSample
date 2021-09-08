package com.terry.jetpackmvvm.sample.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.jetpackmvvm.sample.MainApplication
import com.terry.jetpackmvvm.sample.ui.utiltest.UtilTestViewModel

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    private var isViewVisible = false
    private var isPrepared = false
    private var isLoaded = false
    protected lateinit var binding: T

    override fun getContext(): Context? {
        return if (activity == null) {
            MainApplication().app
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
        isPrepared = true;
//        onVisible();

        init()
    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if (userVisibleHint) {
//            isViewVisible = true
//            onVisible()
//        } else {
//            isViewVisible = false
//            onInvisible()
//        }
//    }
//
//    private fun onVisible() {
//        if (!isPrepared || !isViewVisible) {
//            return
//        }
//        lazyLoadEvery()
//        if (isLoaded) return
//        isLoaded = true
//        lazyLoad()
//    }

//    /**
//     * 界面不可见时自动调用
//     */
//    private fun onInvisible() {
//
//    }

//    /**
//     * fragment处于可见状态时自动调用该方法，实现懒加载，一般用作网络请求
//     */
//    protected open fun lazyLoad() {}
//
//    protected open fun lazyLoadEvery() {}

    protected abstract fun getLayoutId(): Int

    protected abstract fun init()
}