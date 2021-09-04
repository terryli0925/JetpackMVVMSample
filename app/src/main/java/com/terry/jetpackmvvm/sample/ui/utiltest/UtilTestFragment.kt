package com.terry.jetpackmvvm.sample.ui.utiltest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.FragmentUtilTestBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment

class UtilTestFragment : BaseFragment<FragmentUtilTestBinding>() {

    companion object {
        fun newInstance() = UtilTestFragment()
    }

    private lateinit var viewModel: UtilTestViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UtilTestViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_util_test

    override fun init() {
    }

}