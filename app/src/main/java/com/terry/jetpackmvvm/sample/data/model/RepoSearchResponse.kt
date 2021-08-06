package com.terry.jetpackmvvm.sample.data.model

import com.google.gson.annotations.SerializedName

data class RepoSearchResponse (
    @SerializedName("total_count")
    var total: Int,
    @SerializedName("items")
    var items: List<Repo>
)