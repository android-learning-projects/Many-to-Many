package com.example.many_to_many.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.many_to_many.AppApplication
import com.example.many_to_many.R
import com.example.many_to_many.databinding.ActivityMainBinding
import com.example.many_to_many.room.entities.Playlist
import com.example.many_to_many.room.entities.PlaylistSongCrossRef
import com.example.many_to_many.room.entities.Song
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MusicViewModel by viewModels {
        ViewModelFactory((application as AppApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*viewModel.insertPlaylist(Playlist(1,"sample 1"))
        viewModel.insertPlaylist(Playlist(2,"sample 2"))
        viewModel.insertPlaylist(Playlist(3,"sample 3"))
        viewModel.insertPlaylist(Playlist(4,"sample 4"))
        viewModel.insertPlaylist(Playlist(5,"sample 5"))*/

        /*viewModel.insertPlaylist(Playlist(1,"sample 1"))

        binding.button.setOnClickListener {
            viewModel.getPlaylistsWithSongs.observe(this, {
                Log.d("TAG", "onCreate: ${it}")
            })

            viewModel.getSongsWithPlaylists.observe(this, {
                Log.d("TAG", "onCreate: ${it}")
            })
        }*/

        binding.buttonInsertSong.setOnClickListener {
            val id = binding.editTextSongId.text.toString().toInt()
            val name = binding.editTextSongName.text.toString().trim()
            val artist = binding.editTextSongArtistName.text.toString().trim()

            viewModel.insertSong(Song(id, name, artist))
        }


        binding.buttonInsertPlaylist.setOnClickListener {
            val id = binding.editTextPlaylistId.text.toString().toInt()
            val name = binding.editTextPlaylistName.text.toString().trim()

            viewModel.insertPlaylist(Playlist(id, name))
        }


        binding.buttonRelation.setOnClickListener {
            val songId = binding.editTextSongIdR.text.toString().toLong()
            val playlistId = binding.editTextPlaylistIdR.text.toString().toLong()

            viewModel.insertCross(PlaylistSongCrossRef(playlistId = playlistId,songId=songId))
        }

        binding.buttonPlaylistWithSongs.setOnClickListener {
            viewModel.getPlaylistsWithSongs.observe(this, {
                Log.d("TAG", "onCreate: ${it}")
            })
        }

        binding.buttonSongsWithPlaylist.setOnClickListener {
            viewModel.getSongsWithPlaylists.observe(this, {
                Log.d("TAG", "onCreate: ${it}")
            })
        }
    }
}