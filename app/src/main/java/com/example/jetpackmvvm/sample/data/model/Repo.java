package com.example.jetpackmvvm.sample.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Repo {

    public final int id;

    @SerializedName("name")
    @NonNull
    public final String name;

    @SerializedName("full_name")
    public final String fullName;

    @SerializedName("description")
    public final String description;

    @SerializedName("stargazers_count")
    public final int stars;

    @SerializedName("owner")
    @NonNull
    public final Owner owner;

    public Repo(int id, String name, String fullName, String description, Owner owner, int stars) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.owner = owner;
        this.stars = stars;
    }
}
