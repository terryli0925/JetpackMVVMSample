package com.terry.jetpackmvvm.sample.ui.utiltest

import androidx.fragment.app.FragmentManager
import com.terry.jetpackmvvm.sample.ui.utiltest.dialog.ScreenshotDialog

object UtilTestUtils {

    fun showScreenShotDialog(fm: FragmentManager) {
        ScreenshotDialog().show(fm, "")
    }
}