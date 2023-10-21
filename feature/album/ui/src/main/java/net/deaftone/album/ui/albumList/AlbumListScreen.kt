package net.deaftone.album.ui.albumList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.glide.GlideImage
import net.deaftone.data.MainScreenRoutes
import net.deaftone.data.model.album.Album

@Composable
fun AlbumListScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AlbumViewModel = hiltViewModel(),
    onNavigationUp: () -> Unit,
    onItemClick: () -> Unit
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
                AlbumItem(
                    album = album,
                    Modifier.clickable(
                        onClick = {
                            navController.navigate(MainScreenRoutes.AlbumInfo.withId(album.id))
                        }
                    )
                )
            }
        }
    }
}

@Composable
fun AlbumItem(
    album: Album,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    onClick: (() -> Unit)? = null,

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

                album.albumArtist?.let {
                    Text(
                        text = it,
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
}
