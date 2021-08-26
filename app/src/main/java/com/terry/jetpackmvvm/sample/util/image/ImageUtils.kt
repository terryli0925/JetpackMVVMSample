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

import android.view.ViewGroup

import android.graphics.Bitmap

import androidx.annotation.NonNull

import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


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
    fun loadRoundImage(iv: ImageView, url: String) {
        val options: RequestOptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(4)) //圆角
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(iv.context)
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
        Glide.with(context).load(url)
            .apply(RequestOptions.bitmapTransform(transformation))
            .into(iv)
    }

    // 固定宽度
    fun loadDynamicHeightImage(context: Context, imageView: ImageView, url: String) {
        Glide.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
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


    // 固定高度
    fun loadDynamicWidthImage(context: Context, imageView: ImageView, url: String) {
        Glide.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap?>() {
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

    // 原始宽高
    fun dynamicWHImage(context: Context, imageView: ImageView, url: String) {
        Glide.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap?>() {
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
}