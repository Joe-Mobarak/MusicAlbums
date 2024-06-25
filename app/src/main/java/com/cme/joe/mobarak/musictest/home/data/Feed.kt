package com.cme.joe.mobarak.musictest.home.data

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("title")
    val title: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("author")
    val author: Author?,
    @SerializedName("links")
    val links: List<Link>?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("results")
    val results: List<Album>?
)