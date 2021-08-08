package com.terry.jetpackmvvm.sample.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.terry.jetpackmvvm.sample.GlideApp

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String) {
        GlideApp.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}