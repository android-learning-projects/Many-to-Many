package com.example.many_to_many.ui

import androidx.lifecycle.*
import com.example.many_to_many.room.entities.*
import kotlinx.coroutines.launch

class MusicViewModel(private val repository: Repository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val getPlaylistsWithSongs: LiveData<List<PlaylistWithSongs>> = repository.getPlaylistsWithSongs.asLiveData()

    val getSongsWithPlaylists: LiveData<List<SongWithPlaylists>> = repository.getSongsWithPlaylists.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertSong(song: Song) = viewModelScope.launch {
        repository.insertSong(song)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertPlaylist(playlist: Playlist) = viewModelScope.launch {
        repository.insertPlaylist(playlist)
    }

    fun insertCross(crossRef: PlaylistSongCrossRef) = viewModelScope.launch {
        repository.insertCross(crossRef)
    }
}

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MusicViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}