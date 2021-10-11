package com.terry.jetpackmvvm.sample.ui.utiltest

import androidx.lifecycle.ViewModelProvider
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.FragmentUtilTestBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.util.RxUtils
import com.terry.jetpackmvvm.sample.widget.AnimDialog

class UtilTestFragment : BaseFragment<FragmentUtilTestBinding>() {
    private lateinit var viewModel: UtilTestViewModel

    override fun getLayoutId(): Int = R.layout.fragment_util_test

    override fun init() {
        viewModel = ViewModelProvider(this).get(UtilTestViewModel::class.java)

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnScreenshot, {
            UtilTestUtils.showScreenShotDialog(parentFragmentManager)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnAnimDialogZoom, {
            UtilTestUtils.showAnimDialog(requireContext(), AnimDialog.AnimInType.CENTER_ZOOM)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnAnimDialogTop, {
            UtilTestUtils.showAnimDialog(requireContext(), AnimDialog.AnimInType.TOP)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnAnimDialogBottom, {
            UtilTestUtils.showAnimDialog(requireContext(), AnimDialog.AnimInType.BOTTOM)
        })
    }

    companion object {
        fun newInstance() = UtilTestFragment()
    }
}