package com.cme.joe.mobarak.musictest.home.data

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("feed") val feed: Feed?
)