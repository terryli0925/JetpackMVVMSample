package com.terry.jetpackmvvm.sample.ui.utiltest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.FragmentUtilTestBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.widget.AnimDialog

class UtilTestFragment : BaseFragment<FragmentUtilTestBinding>() {
    private lateinit var viewModel: UtilTestViewModel

    override fun getLayoutId(): Int = R.layout.fragment_util_test

    override fun init() {
        viewModel = ViewModelProvider(this).get(UtilTestViewModel::class.java)

        binding.btnScreenshot.setOnClickListener { UtilTestUtils.showScreenShotDialog(parentFragmentManager) }
        binding.btnAnimDialogZoom.setOnClickListener { UtilTestUtils.showAnimDialog(requireContext(), AnimDialog.AnimInType.CENTER_ZOOM) }
        binding.btnAnimDialogTop.setOnClickListener { UtilTestUtils.showAnimDialog(requireContext(), AnimDialog.AnimInType.TOP) }
        binding.btnAnimDialogBottom.setOnClickListener { UtilTestUtils.showAnimDialog(requireContext(), AnimDialog.AnimInType.BOTTOM) }
    }

    companion object {
        fun newInstance() = UtilTestFragment()
    }
}