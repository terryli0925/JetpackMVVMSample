package com.terry.jetpackmvvm.sample.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.terry.jetpackmvvm.sample.GlideApp
import com.terry.jetpackmvvm.sample.util.image.ImageUtils

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String) {
        ImageUtils.loadRoundImage(imageView, url, 8)
    }
}