package com.example.many_to_many.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.many_to_many.AppApplication
import com.example.many_to_many.R
import com.example.many_to_many.room.entities.Playlist
import com.example.many_to_many.room.entities.PlaylistSongCrossRef
import com.example.many_to_many.room.entities.Song
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MusicViewModel by viewModels {
        ViewModelFactory((application as AppApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*viewModel.insertPlaylist(Playlist(1,"sample 1"))
        viewModel.insertPlaylist(Playlist(2,"sample 2"))
        viewModel.insertPlaylist(Playlist(3,"sample 3"))
        viewModel.insertPlaylist(Playlist(4,"sample 4"))
        viewModel.insertPlaylist(Playlist(5,"sample 5"))*/

        viewModel.insertPlaylist(Playlist(1,"sample 1"))

        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.getPlaylistsWithSongs.observe(this, {
                Log.d("TAG", "onCreate: ${it}")
            })

            viewModel.getSongsWithPlaylists.observe(this, {
                Log.d("TAG", "onCreate: ${it}")
            })
        }


    }
}