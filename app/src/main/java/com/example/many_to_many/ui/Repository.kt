package com.example.many_to_many.ui

import androidx.annotation.WorkerThread
import com.example.many_to_many.room.dao.SongsDao
import com.example.many_to_many.room.entities.*
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class Repository(private val dao: SongsDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val getPlaylistsWithSongs: Flow<List<PlaylistWithSongs>> = dao.getPlaylistsWithSongs()
    val getSongsWithPlaylists: Flow<List<SongWithPlaylists>> = dao.getSongsWithPlaylists()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertSong(song: Song) {
        dao.insertSong(song)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertPlaylist(playlist: Playlist) {
        dao.insertPlaylist(Playlist(10,"play list one"))
        dao.insertSong(Song(2,"song 1","ilaya raja"))
        dao.insertSong(Song(3,"song 2","ilaya raja"))
        dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 2))
        dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 3))

    }
}