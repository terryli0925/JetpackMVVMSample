package com.terry.jetpackmvvm.sample.util

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

object ScreenUtils {
    /**
     * dp to px
     */
    fun dp2px(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        ).toInt()
    }

    /**
     * px to dp
     */
    fun px2dp(px: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            px.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }

    fun getScreenDisplay(): IntArray {
        val width = getScreenWidth() // 手机屏幕的宽度
        val height = getScreenHeight() // 手机屏幕的高度
        return intArrayOf(width, height)
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getStatusHeight(context: Context?): Int {
        var statusHeight = 0
        context ?: return statusHeight
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val instance = clazz.newInstance()
            val height = clazz.getField("status_bar_height")[instance].toString().toInt()
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusHeight
    }

    fun getStatusBarHeight(context: Context?): Int {
        context ?: return 0
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        val height = resources.getDimensionPixelSize(resourceId);
        return height
    }
}