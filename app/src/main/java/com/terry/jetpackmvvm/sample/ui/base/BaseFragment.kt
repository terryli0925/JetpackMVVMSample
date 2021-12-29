package com.terry.jetpackmvvm.sample.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.terry.jetpackmvvm.sample.MainApplication
import com.terry.jetpackmvvm.sample.di.component.DaggerFragmentComponent
import com.terry.jetpackmvvm.sample.di.component.FragmentComponent

abstract class BaseFragment<VB : ViewDataBinding>(private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
        Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    private var isFirstLoad = false

//    override fun getContext(): Context? {
//        return if (activity == null) {
//            MainApplication.app
//        } else activity
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initInject()
    }

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

    protected fun getFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent
            .builder()
            .appComponent((requireActivity().application as MainApplication).appComponent)
            .build()
    }

    protected fun lazyLoad() {}

    protected fun lazyLoadEvery() {}

    protected abstract fun init()

    protected abstract fun initInject()

}