package com.terry.jetpackmvvm.sample.ui.utiltest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.DialogVerificationCodeBinding
import com.terry.jetpackmvvm.sample.databinding.FragmentUtilTestBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseAnimDialog
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.util.RxUtils
import com.terry.jetpackmvvm.sample.util.ScreenUtils

class UtilTestFragment : BaseFragment<FragmentUtilTestBinding>() {
    private lateinit var viewModel: UtilTestViewModel

    override fun getLayoutId(): Int = R.layout.fragment_util_test

    override fun init() {
        viewModel = ViewModelProvider(this).get(UtilTestViewModel::class.java)

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnScreenshot, {
            UtilTestUtils.showScreenShotDialog(parentFragmentManager)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnAnimDialog, {
            UtilTestUtils.showVerificationCodeDialog(
                requireContext()
            )
        })
    }

    companion object {
        fun newInstance() = UtilTestFragment()
    }
}