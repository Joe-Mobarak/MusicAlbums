package com.cme.joe.mobarak.musictest.home.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.joe.mobarak.musictest.home.data.Album
import com.cme.joe.mobarak.musictest.home.data.Result
import com.cme.joe.mobarak.musictest.home.network.service.AlbumsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewmodel @Inject constructor(
    private val apiService: AlbumsAPI
) : ViewModel() {

    private val _albumsState = MutableStateFlow<Result<List<Album>>>(Result.Loading)
    val albumsState: StateFlow<Result<List<Album>>> = _albumsState

    init {
        fetchTopAlbums()
    }


    private fun fetchTopAlbums() {
        viewModelScope.launch {
            try {
                val feed = apiService.getTopAlbums()
                _albumsState.value = Result.Success(feed.feed?.results ?: emptyList())
            } catch (e: Exception) {
                _albumsState.value = Result.Error("Error fetching albums: ${e.message}")
            }
        }
    }
}
