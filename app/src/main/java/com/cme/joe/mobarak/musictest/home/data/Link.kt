package com.cme.joe.mobarak.musictest.home.data

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("self")
    val self: String?
)