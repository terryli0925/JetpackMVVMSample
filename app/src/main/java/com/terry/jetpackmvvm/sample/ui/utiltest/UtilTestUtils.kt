package com.terry.jetpackmvvm.sample.ui.utiltest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.terry.jetpackmvvm.sample.databinding.DialogVerificationCodeBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseAnimDialog
import com.terry.jetpackmvvm.sample.ui.utiltest.dialog.ScreenshotDialog
import com.terry.jetpackmvvm.sample.widget.verificationcodeview.VerificationCodeView

object UtilTestUtils {

    fun showScreenShotDialog(fm: FragmentManager) {
        ScreenshotDialog().show(fm, "")
    }

    fun showVerificationCodeDialog(context: Context) {
        val binding = DialogVerificationCodeBinding.inflate(LayoutInflater.from(context))
        val dialog = BaseAnimDialog(context)
        dialog.contentView(binding.root)
            .animType(BaseAnimDialog.AnimInType.CENTER)
            .show()
        binding.verificationCodeView.onCodeFinishListener =
            object : VerificationCodeView.OnCodeFinishListener {
                override fun onTextChange(view: View, content: String) {
                }

                override fun onComplete(view: View, content: String) {
                    Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
    }
}