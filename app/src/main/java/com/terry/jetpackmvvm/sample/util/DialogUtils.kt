package com.terry.jetpackmvvm.sample.util

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

object DialogUtils {

    fun isDialogShowing(fg: FragmentManager, tag: String): Boolean {
        val fragment = fg.findFragmentByTag(tag) as DialogFragment?
        return fragment != null && fragment.dialog != null && fragment.dialog!!.isShowing && !fragment.isRemoving
    }
}