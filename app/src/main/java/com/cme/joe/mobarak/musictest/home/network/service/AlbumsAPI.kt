package com.cme.joe.mobarak.musictest.home.network.service

import com.cme.joe.mobarak.musictest.home.data.FeedResponse
import retrofit2.http.GET

interface AlbumsAPI {
    @GET("api/v2/us/music/most-played/100/albums.json")
    suspend fun getTopAlbums(): FeedResponse
}
