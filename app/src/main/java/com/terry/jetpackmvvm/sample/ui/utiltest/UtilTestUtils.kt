package com.terry.jetpackmvvm.sample.ui.utiltest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.DialogLoadingBinding
import com.terry.jetpackmvvm.sample.ui.utiltest.dialog.ScreenshotDialog
import com.terry.jetpackmvvm.sample.util.ScreenUtils
import com.terry.jetpackmvvm.sample.widget.AnimDialog

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
}