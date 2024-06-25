package com.cme.joe.mobarak.musictest.home.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.cme.joe.mobarak.musictest.home.data.Album
import com.cme.joe.mobarak.musictest.home.data.Result
import com.cme.joe.mobarak.musictest.ui.theme.MusicTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment(navController: NavHostController) : Fragment() {

    private val viewModel: AlbumsViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MusicTestTheme {
                    // Observe albumsState from ViewModel
                    val albumsState = viewModel.albumsState.collectAsState(initial = Result.Loading)

                    // Display content based on albumsState
                    when (val result = albumsState.value) {
                        is Result.Loading -> {
                            // Show loading indicator or placeholder
                            LoadingScreen()
                        }

                        is Result.Success -> {
                            // Show list of albums
                            AlbumListContent(albums = result.data)
                        }

                        is Result.Error -> {
                            // Show error message
                            ErrorScreen(errorMessage = result.message)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LoadingScreen() {
        // Placeholder for loading screen
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun ErrorScreen(errorMessage: String) {
        // Placeholder for error screen
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Error: $errorMessage", color = Color.Red)
        }
    }

    @Composable
    fun AlbumListContent(albums: List<Album>) {
        LazyColumn {
            items(albums) { album ->
                AlbumListItem(album = album)
            }
        }
    }

    @Composable
    fun AlbumListItem(album: Album) {
        // Replace with your desired layout for each album item
        // Example: Display album name, artist, and thumbnail
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = album.name.toString(), style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = album.artistName.toString(), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = rememberAsyncImagePainter(album.url.toString()),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
    }
}
