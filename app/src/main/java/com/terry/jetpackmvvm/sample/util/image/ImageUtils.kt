package com.terry.jetpackmvvm.sample.util.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.terry.jetpackmvvm.sample.GlideApp
import com.terry.jetpackmvvm.sample.util.ScreenUtils
import android.graphics.drawable.Drawable

import android.graphics.Bitmap

import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64


object ImageUtils {

    /**
     * Load image
     */
    fun load(iv: ImageView, url: String, options: RequestOptions?) {
        val defaultOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        GlideApp.with(iv.context)
            .load(url)
            .apply(options ?: defaultOptions)
            .into(iv)
    }

    /**
     * Load image with round
     */
    fun loadRoundImage(iv: ImageView, url: String, radius: Int) {
        val options: RequestOptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(radius)) //圆角
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        GlideApp.with(iv.context)
            .load(url)
            .apply(options)
            .into(iv)
    }

    /**
     * Load image with top round
     */
    fun loadTopRoundImage(context: Context, iv: ImageView, url: String) {
        val transformation = RoundedCornersTransformation(
            context,
            ScreenUtils.dp2px(5.0F),
            RoundedCornersTransformation.CornerType.TOP
        )
        GlideApp.with(context).load(url)
            .apply(RequestOptions.bitmapTransform(transformation))
            .into(iv)
    }

    /**
     * Load image with fixed width and dynamic height
     */
    fun loadDynamicHeightImage(context: Context, imageView: ImageView, url: String) {
        GlideApp.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val imageWidth = resource.width
                val imageHeight = resource.height

                //宽度固定,然后根据原始宽高比得到此固定宽度需要的高度
                val height = imageView.width * imageHeight / imageWidth
                val para = imageView.layoutParams
                para.width = imageView.width
                para.height = height
                imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    }


    /**
     * Load image with fixed height and dynamic width
     */
    fun loadDynamicWidthImage(context: Context, imageView: ImageView, url: String) {
        GlideApp.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                val imageWidth = resource.width
                val imageHeight = resource.height

                val para = imageView.layoutParams
                para.width = imageView.height * imageWidth / imageHeight
                para.height = imageView.height
                imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    }

    /**
     * Load image with original width and height
     */
    fun dynamicWHImage(context: Context, imageView: ImageView, url: String) {
        GlideApp.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap?>?
            ) {
                val imageWidth = resource.width
                val imageHeight = resource.height
                val para = imageView.layoutParams
                para.height = imageHeight
                para.width = imageWidth
                imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    /**
     * Base64 transform to Bitmap
     */
    fun getBitmapFromBase64(base64: String): Bitmap {
        val decodedString = Base64.decode(base64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    /**
     * 等比例放大縮小
     */
    fun scaleBitmap(bitmap: Bitmap, scale: Float): Bitmap {
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}