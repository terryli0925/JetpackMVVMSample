package com.terry.jetpackmvvm.sample.ui.utiltest

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.DialogLoadingBinding
import com.terry.jetpackmvvm.sample.databinding.DialogVerificationCodeBinding
import com.terry.jetpackmvvm.sample.ui.utiltest.dialog.ScreenshotDialog
import com.terry.jetpackmvvm.sample.util.ScreenUtils
import com.terry.jetpackmvvm.sample.widget.AnimDialog
import com.terry.jetpackmvvm.sample.widget.VerificationCodeView

object UtilTestUtils {

    fun showScreenShotDialog(fm: FragmentManager) {
        ScreenshotDialog().show(fm, "")
    }

    fun showAnimDialog(context: Context, type: AnimDialog.AnimInType) {
        val dialog = AnimDialog(context)
        val binding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        dialog.contentView(binding.root)
            .animType(type).show()
    }

    fun showVerificationViewDialog(context: Context) {
        val dialog = AnimDialog(context)
        val binding = DialogVerificationCodeBinding.inflate(LayoutInflater.from(context))
        binding.verificationCodeView.onCodeFinishListener =
            object : VerificationCodeView.OnCodeFinishListener {
                override fun onTextChange(view: View, content: String) {
                }

                override fun onComplete(view: View, content: String) {
                    Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        dialog.contentView(binding.root).cancelable(false).show()
    }
}