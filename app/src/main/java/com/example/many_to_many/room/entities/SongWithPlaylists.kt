package com.example.many_to_many.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SongWithPlaylists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playlistId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>?=null
)