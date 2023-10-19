package net.deaftone.album.ui.albumInfo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.landscapist.glide.GlideImage
import net.deaftone.album.data.model.Album

@Composable
fun AlbumInfoScreen(
    onNavigationUp: () -> Unit,
    viewModel: AlbumInfoViewModel = hiltViewModel(),
) {
    when (val state = viewModel.album.collectAsStateWithLifecycle().value) {

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
        is AlbumInfoUiState.Loaded ->
            AlbumColumn(album = state.data)
           /* Column(
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(280.dp)
                ) {
                    GlideImage(
                        imageModel = { "http://192.168.1.2:3030/albums/${state.data.id}/cover" },
                        // shows an error text message when request failed.
                        failure = {
                            Text(text = "image request failed.")
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }

                LazyColumn() {
                    items(state.data.songs!!) { song ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .height(72.dp)
                            ) {
                                Text(
                                    text = song.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = LocalContentColor.current,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                    }
                }

            }*/
    }
}

@Composable
fun AlbumColumn(
    album: Album,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    Column() {

        GlideImage(
            imageModel = { "http://192.168.1.2:3030/albums/${album.id}/cover" },
            // shows an error text message when request failed.
            failure = {
                Text(text = "image request failed.")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.medium)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.width(150.dp)
        ) {
            Text(album.name, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodyLarge)
            Row(
                // horizontalArrangement = Arrangement.spacedBy(AppTheme.specs.paddingTiny),
                verticalAlignment = Alignment.CenterVertically
            ) {

                album.albumArtist?.let {
                    Text(
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
