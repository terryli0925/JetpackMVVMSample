package com.example.jetpackmvvm.sample.data.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("login")
    @NonNull
    public final String login;

    @SerializedName("avatar_url")
    public final String avatarUrl;

    @SerializedName("url")
    public final String url;

    public Owner(@NonNull String login, String avatarUrl, String url) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.url = url;
    }
}
