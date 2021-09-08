package com.terry.jetpackmvvm.sample.ui.utiltest.dialog

import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.DialogScreenshotBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseDialogFragment

class ScreenshotDialog : BaseDialogFragment<DialogScreenshotBinding>() {
    override fun getLayoutId() = R.layout.dialog_screenshot

    override fun onStart() {
        super.onStart()
//        setWidthRatio(1.0)
//        binding.bg.scaleFactor = 1
        binding.bg.refreshBG(requireActivity())
    }
}