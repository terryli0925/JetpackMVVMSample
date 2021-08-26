package com.terry.jetpackmvvm.sample.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.AttributeSet
import android.widget.ImageView
import com.terry.jetpackmvvm.sample.util.ScreenUtils

@SuppressLint("AppCompatCustomView")
class BlurBGImageView : ImageView {
    var overlay: Bitmap? = null
    var scaleFactor = 2
    var radius = 8

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    fun refreshBG(activity: Activity) {
        var bitmap1: Bitmap? = null
        try {
            bitmap1 = getScreenShot(activity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (bitmap1 != null) {
            blur(bitmap1, this, radius.toFloat()) //模糊处理
            bitmap1.recycle()
        }
    }

    private fun blur(bkg: Bitmap, view: ImageView, radius: Float) {
        if (overlay != null) {
            overlay!!.recycle()
        }
        overlay = Bitmap.createScaledBitmap(bkg, bkg.width / scaleFactor, bkg.height / scaleFactor, false)
        overlay = blur(context, overlay, radius) //高斯模糊
        view.setImageBitmap(overlay)
    }

    private fun blur(context: Context, image: Bitmap?, radius: Float): Bitmap {
        val rs = RenderScript.create(context)
        val outputBitmap = Bitmap.createBitmap(image!!.width, image.height, Bitmap.Config.ARGB_8888)
        val `in` = Allocation.createFromBitmap(rs, image)
        val out = Allocation.createFromBitmap(rs, outputBitmap)
        val intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        intrinsicBlur.setRadius(radius)
        intrinsicBlur.setInput(`in`)
        intrinsicBlur.forEach(out)
        out.copyTo(outputBitmap)
        image.recycle()
        rs.destroy()
        return outputBitmap
    }

    /**
     * 获取当前屏幕截图，不包含状态栏（Status Bar）。
     *
     * @param activity activity
     * @return Bitmap
     */
    private fun getScreenShot(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val statusBarHeight = ScreenUtils.getStatusHeight(activity)
        val width = ScreenUtils.getScreenWidth()
        val height = ScreenUtils.getScreenHeight()

        val ret = Bitmap.createBitmap(bmp, statusBarHeight, statusBarHeight, width, height)
        view.destroyDrawingCache()
        return ret
    }
}
