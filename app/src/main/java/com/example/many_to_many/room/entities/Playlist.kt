package com.example.many_to_many.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @PrimaryKey val playlistId: Int,
    val playlistName: String?=null
)