package com.terry.jetpackmvvm.sample.ui.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.terry.jetpackmvvm.sample.util.ScreenUtils


abstract class BaseDialogFragment<T : ViewDataBinding> : DialogFragment() {
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root

    }

    override fun show(manager: FragmentManager, tag: String?) {
        val ft: FragmentTransaction = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    // ==== 在 super.onStart(); 後呼叫 =====================
    protected fun setGravityBottom() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setGravity(Gravity.BOTTOM)
    }

    protected fun setRatioWH(widthToScreen: Double, heightToWidth: Double) { // ex: 0.8, 0.4
        val w = (ScreenUtils.getScreenWidth() * widthToScreen).toInt()
        val h = (w * heightToWidth).toInt()
        dialog?.window?.setLayout(w, h)
    }

    protected fun setWidthRatio(widthToScreen: Double) { // 0.8
        val w = (ScreenUtils.getScreenWidth() * widthToScreen).toInt()
        dialog?.window?.setLayout(w, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    protected fun setFullScreen() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    protected abstract fun getLayoutId(): Int
}