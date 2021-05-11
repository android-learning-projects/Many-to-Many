package com.example.many_to_many.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.many_to_many.data.room.dao.SongsDao
import com.example.many_to_many.data.room.entities.Playlist
import com.example.many_to_many.data.room.entities.PlaylistSongCrossRef
import com.example.many_to_many.data.room.entities.Song

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Song::class, Playlist::class,PlaylistSongCrossRef::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): SongsDao

    companion object {
        val DATABASE_NAME: String = "app_database"
    }
}