package net.deaftone.eurydice.ui.album

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.deaftone.eurydice.ui.navigation.MainScreenRoutes

@Composable
fun AlbumInfoScreen(
    onNavigationUp: () -> Unit,
    viewModel: AlbumInfoViewModel = hiltViewModel(),
    ) {
    when (val state = viewModel.album.collectAsState().value) {

        is AlbumInfoUiState.Empty -> Text(
            text = "No data",
            modifier = Modifier.padding(16.dp)
        )
        is AlbumInfoUiState.Loading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        is AlbumInfoUiState.Error -> Text(text = state.message)
        is AlbumInfoUiState.Loaded -> LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(state.data.songs!!) { song ->
                Text(text = song.title)
            }
        }
    }
}