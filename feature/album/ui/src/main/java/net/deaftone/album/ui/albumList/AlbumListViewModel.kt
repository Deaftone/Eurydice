package net.deaftone.album.ui.albumList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.deaftone.album.data.model.Album
import net.deaftone.album.data.service.AlbumService

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumService: AlbumService) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()
    private val _albumList = MutableStateFlow<AlbumUiState>(AlbumUiState.Empty)
    val albumList: StateFlow<AlbumUiState> = _albumList

    init {
        getAlbumData()
    }

    private fun getAlbumData() {
        _albumList.value = AlbumUiState.Loading
        viewModelScope.launch {
            try {
                _albumList.value = AlbumUiState.Loaded(albumService.getAllAlbums().body()!!.albums)
            } catch (ex: Exception) {
                println(ex)
                _albumList.value = AlbumUiState.Error("Failed to load albums")
            }
        }
    }
}

sealed class AlbumUiState {
    object Empty : AlbumUiState()
    object Loading : AlbumUiState()
    class Loaded(val data: List<Album>) : AlbumUiState()
    class Error(val message: String) : AlbumUiState()
}
