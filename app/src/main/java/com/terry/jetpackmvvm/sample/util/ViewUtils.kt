package com.terry.jetpackmvvm.sample.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View


object ViewUtils {

    fun loadBitmapFromView(view: View): Bitmap? {
        val w: Int = view.width
        val h: Int = view.height
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        }
        //        else {
//            canvas.drawColor(Color.WHITE);
//        }
        view.layout(0, 0, w, h)
        view.draw(canvas)
        return bmp
    }

}