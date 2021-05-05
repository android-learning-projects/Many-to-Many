package com.example.many_to_many.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.many_to_many.room.dao.SongsDao
import com.example.many_to_many.room.entities.Playlist
import com.example.many_to_many.room.entities.PlaylistSongCrossRef
import com.example.many_to_many.room.entities.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Song::class, Playlist::class,PlaylistSongCrossRef::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): SongsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.dao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(dao: SongsDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
       /*     dao.deleteAllPlaylist()
            dao.deleteAllSongs()

            for (i in 1..9) {
                dao.insertSong(Song(i, "Song $i", "Artist $i"))
                dao.insertPlaylist(Playlist(i * 10, "Playlist $i"))
            }

            dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 1))
            dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 2))
            dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 3))
            dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 4))

            dao.insert(PlaylistSongCrossRef(playlistId = 20, songId = 1))
            dao.insert(PlaylistSongCrossRef(playlistId = 20, songId = 2))
            dao.insert(PlaylistSongCrossRef(playlistId = 20, songId = 3))
            dao.insert(PlaylistSongCrossRef(playlistId = 20, songId = 4))

            dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 2))
            dao.insert(PlaylistSongCrossRef(playlistId = 20, songId = 2))
            dao.insert(PlaylistSongCrossRef(playlistId = 30, songId = 2))
            dao.insert(PlaylistSongCrossRef(playlistId = 40, songId = 2))


            dao.insert(PlaylistSongCrossRef(playlistId = 10, songId = 1))
            dao.insert(PlaylistSongCrossRef(playlistId = 20, songId = 2))
            dao.insert(PlaylistSongCrossRef(playlistId = 30, songId = 3))
            dao.insert(PlaylistSongCrossRef(playlistId = 40, songId = 4))
*/

        }
    }
}