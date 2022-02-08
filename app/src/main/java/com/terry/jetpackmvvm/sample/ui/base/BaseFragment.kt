package com.terry.jetpackmvvm.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.terry.jetpackmvvm.sample.util.SystemUtils

abstract class BaseFragment<VB : ViewBinding>(private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
        Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    private var isFirstLoad = false

//    override fun getContext(): Context? {
//        return if (activity == null) {
//            MainApplication.app
//        } else activity
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateMethod.invoke(inflater, container, false)
//        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
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
        SystemUtils.hideSoftInput(requireActivity())
        _binding = null
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

    protected abstract fun init()
}