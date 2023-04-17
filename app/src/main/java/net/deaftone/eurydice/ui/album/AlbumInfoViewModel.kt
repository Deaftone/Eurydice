package net.deaftone.eurydice.ui.album

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.deaftone.eurydice.data.entities.Album
import net.deaftone.eurydice.data.service.AlbumService
import net.deaftone.eurydice.ui.navigation.MainScreenRoutes

@HiltViewModel
class AlbumInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val albumService: AlbumService
) : ViewModel() {
    private val _album = MutableStateFlow<AlbumInfoUiState>(AlbumInfoUiState.Empty)
    val album: StateFlow<AlbumInfoUiState> = _album

    init {
        val id = savedStateHandle.get<String>(MainScreenRoutes.ARG_RESOURCE_ID)
        println(id)
        if (id != null) {
            getAlbumData(id)
        }
    }

    fun getAlbumData(id: String) {
        _album.value = AlbumInfoUiState.Loading
        viewModelScope.launch {
            _album.value = AlbumInfoUiState.Loaded(albumService.getAlbum(id).body()!!.album)
        }
    }
}

sealed class AlbumInfoUiState {
    object Empty : AlbumInfoUiState()
    object Loading : AlbumInfoUiState()
    class Loaded(val data: Album) : AlbumInfoUiState()
    class Error(val message: String) : AlbumInfoUiState()
}
