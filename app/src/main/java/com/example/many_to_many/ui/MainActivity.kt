package com.example.many_to_many.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.many_to_many.AppApplication
import com.example.many_to_many.R
import com.example.many_to_many.databinding.ActivityMainBinding
import com.example.many_to_many.data.room.entities.Playlist
import com.example.many_to_many.data.room.entities.PlaylistSongCrossRef
import com.example.many_to_many.data.room.entities.Song
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MusicViewModel by viewModels()

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

        viewModel.insertPlaylist(Playlist(1,"sample 1"))

        viewModel.getPlaylistsWithSongs.observe(this, {
            Timber.d( "onCreate: ${it}")
        })

        viewModel.getSongsWithPlaylists.observe(this, {
            Timber.d( "onCreate: ${it}")
        })

        binding.buttonInsertSong.setOnClickListener {
//            val id = binding.editTextSongId.text.toString().toInt()
            val name = binding.editTextSongName.text.toString().trim()
            val artist = binding.editTextSongArtistName.text.toString().trim()

            viewModel.insertSong(Song(0, name, artist))
        }


        binding.buttonInsertPlaylist.setOnClickListener {
//            val id = binding.editTextPlaylistId.text.toString().toInt()
            val name = binding.editTextPlaylistName.text.toString().trim()

            viewModel.insertPlaylist(Playlist(0, name))
        }


        binding.buttonRelation.setOnClickListener {
            val songId = binding.editTextSongIdR.text.toString().toLong()
            val playlistId = binding.editTextPlaylistIdR.text.toString().toLong()

            viewModel.insertCross(PlaylistSongCrossRef(playlistId = playlistId, songId = songId))
        }

        binding.buttonPlaylistWithSongs.setOnClickListener {
            viewModel.getPlaylistsWithSongs.observe(this, {
                Timber.d( "onCreate: ${it}")
            })
        }

        binding.buttonSongsWithPlaylist.setOnClickListener {
            viewModel.getSongsWithPlaylists.observe(this, {
                Timber.d( "onCreate: ${it}")
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_song -> {
                startActivity(Intent(this, TabbedActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}