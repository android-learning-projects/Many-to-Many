package com.example.many_to_many.ui.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.many_to_many.data.room.entities.Song
import com.example.many_to_many.databinding.LayoutSongItemBinding

class SongAdapter : RecyclerView.Adapter<SongAdapter.MyViewHolder>() {

    private val list = mutableListOf<Song>()

    fun update(list: List<Song>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.MyViewHolder {
        val view =
            LayoutSongItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position], position)

    }

    class MyViewHolder(view: LayoutSongItemBinding) : RecyclerView.ViewHolder(view.root) {
        private val binding = view
        fun bind(song: Song, position: Int) {
            binding.textViewSongName.text = song.songName
            binding.textViewArtist.text = song.artist
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}