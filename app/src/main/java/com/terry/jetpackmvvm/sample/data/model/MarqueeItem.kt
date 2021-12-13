package com.terry.jetpackmvvm.sample.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarqueeItem(var content: String) : Parcelable
