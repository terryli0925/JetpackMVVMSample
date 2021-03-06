package com.terry.jetpackmvvm.sample.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
    @field:SerializedName("url")
    val url: String
)