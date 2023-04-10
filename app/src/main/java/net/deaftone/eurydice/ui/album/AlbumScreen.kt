package net.deaftone.eurydice.ui.album

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import net.deaftone.eurydice.R
import net.deaftone.eurydice.data.entities.Album

@Composable
fun AlbumActivity(
    onItemClick: () -> Unit,
    viewModel: AlbumViewModel = hiltViewModel(),
    onNavigationUp: () -> Unit
) {
    when (val state = viewModel.albumList.collectAsState().value) {

        is AlbumUiState.Empty -> Text(
            text = "No data",
            modifier = Modifier.padding(16.dp)
        )
        is AlbumUiState.Loading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        is AlbumUiState.Error -> Text(text = state.message)
        is AlbumUiState.Loaded -> LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(state.data) { album ->
                AlbumItem(album = album)
            }
        }
    }
}

@Composable
fun AlbumItem(
    album: Album,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(72.dp)
        ) {
            GlideImage(
                imageModel = { R.drawable.unknown },
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
            /*Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(
                            *//*	if (album.songs.isNotEmpty()) album.songs[0].albumPath.toUri()
                                else R.drawable.ic_music_unknown*//*
                            "http://localhost:3030/albums/${album.albumId}/cover"
                        )
                        .error(R.drawable.ic_music_unknown)
                        .placeholder(R.drawable.ic_music_unknown)
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
            )*/

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = album.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = LocalContentColor.current,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = album.albumArtist,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = LocalContentColor.current,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}