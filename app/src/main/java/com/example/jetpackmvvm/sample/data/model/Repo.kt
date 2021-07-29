package com.example.jetpackmvvm.sample.data.model

import com.google.gson.annotations.SerializedName

data class Repo(
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("full_name")
    val fullName: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("owner")
    val owner: Owner,
    @field:SerializedName("stargazers_count")
    val stars: Int
)