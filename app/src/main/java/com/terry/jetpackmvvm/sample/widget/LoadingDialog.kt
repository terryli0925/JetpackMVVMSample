package com.terry.jetpackmvvm.sample.widget

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.terry.jetpackmvvm.sample.databinding.DialogLoadingBinding
import com.terry.jetpackmvvm.sample.util.ScreenUtils
import com.terry.jetpackmvvm.sample.util.activitymanager.ActivityManager

class LoadingDialog : DialogFragment() {
    private lateinit var binding: DialogLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLoadingBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        resizeDialogFragment()
    }

    private fun resizeDialogFragment() {
        val w = ScreenUtils.dp2px(100f)
        val h = ScreenUtils.dp2px(100f)
        dialog?.window?.setLayout(w, h)
    }

    override fun onPause() {
        super.onPause()
        myDialog?.dismissAllowingStateLoss()
        myDialog = null
        loadNum = 0
    }

    companion object {
        var myDialog: LoadingDialog? = null
        var loadNum = 0

        fun show() {
            if (myDialog == null) {
                val activity: Activity = ActivityManager.getTagActivity()
                if (!activity.isFinishing) {
                    loadNum = 1
                    myDialog = LoadingDialog()
                    myDialog?.show((activity as FragmentActivity).supportFragmentManager, "loading")
                }
            } else {
                loadNum++
            }
        }

        fun dismiss() {
            loadNum--
            if (loadNum <= 0) {
                myDialog?.dismissAllowingStateLoss()
                myDialog = null
                loadNum = 0
            }
        }
    }
}