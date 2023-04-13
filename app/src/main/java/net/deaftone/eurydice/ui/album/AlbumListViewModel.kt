package net.deaftone.eurydice.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.deaftone.eurydice.data.entities.Album
import net.deaftone.eurydice.data.service.AlbumService
import javax.inject.Inject

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

    fun getAlbumData() {
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
