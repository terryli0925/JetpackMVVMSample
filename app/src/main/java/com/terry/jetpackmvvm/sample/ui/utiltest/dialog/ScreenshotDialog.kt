package com.terry.jetpackmvvm.sample.ui.utiltest.dialog

import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.DialogScreenshotBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseDialogFragment
import com.terry.jetpackmvvm.sample.util.ScreenUtils

class ScreenshotDialog : BaseDialogFragment<DialogScreenshotBinding>() {
    override fun getLayoutId() = R.layout.dialog_screenshot

    override fun onStart() {
        super.onStart()

        activity?.let {
            // 避免圖片無法正常顯示
            val lp = binding.bg.layoutParams
            lp.width = (ScreenUtils.getScreenWidth() * RATIO).toInt()
            lp.height = (ScreenUtils.getScreenHeight() * RATIO).toInt()
            binding.bg.layoutParams = lp
            binding.bg.refreshBG(requireActivity())
        }
    }

    companion object {
        private const val RATIO = 0.6
    }
}