package com.terry.jetpackmvvm.sample.util

import android.app.Activity
import android.content.*
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.terry.jetpackmvvm.sample.MainApplication

object SystemUtils {

    fun copyLink(copyLink: String?) {
        val clipData = ClipData.newPlainText("", copyLink)
        (MainApplication.app?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(
            clipData
        )
    }

    // 隱藏軟鍵盤
    fun hideSoftInput(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }

    // 隱藏軟鍵盤(for dialog)
    fun hideSoftInput(activity: Activity, view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Open url in browser
    fun openBrowser(context: Context?, url: String?) {
        if (context == null || url.isNullOrEmpty()) return

        try {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }
    }

}