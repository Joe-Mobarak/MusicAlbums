package com.cme.joe.mobarak.musictest.home.data

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)