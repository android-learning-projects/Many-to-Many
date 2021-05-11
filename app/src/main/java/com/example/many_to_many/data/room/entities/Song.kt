package com.example.many_to_many.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey(autoGenerate = true) val songId: Int,
    val songName: String? = null,
    val artist: String? = null
)