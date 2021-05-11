package com.example.many_to_many.ui

import androidx.annotation.WorkerThread
import com.example.many_to_many.data.room.dao.SongsDao
import com.example.many_to_many.data.room.entities.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO

class Repository
@Inject
constructor(private val dao: SongsDao) {


    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val getPlaylistsWithSongs: Flow<List<PlaylistWithSongs>> = dao.getPlaylistsWithSongs()
    val getSongsWithPlaylists: Flow<List<SongWithPlaylists>> = dao.getSongsWithPlaylists()
    val getAllSongs: Flow<List<Song>> = dao.getAllSongs()
    val getAllPlaylist: Flow<List<Playlist>> = dao.getAllPlaylists()


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
        dao.insertPlaylist(playlist)
//        dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 3))

    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCross(cross: PlaylistSongCrossRef) {
        dao.insert(cross)

    }
}