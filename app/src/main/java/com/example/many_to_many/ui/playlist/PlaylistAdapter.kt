package com.example.many_to_many.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.many_to_many.data.room.entities.Playlist
import com.example.many_to_many.data.room.entities.Song
import com.example.many_to_many.databinding.LayoutPlaylistItemBinding
import com.example.many_to_many.databinding.LayoutSongItemBinding

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.MyViewHolder>() {

    private val list = mutableListOf<Playlist>()

    fun update(list: List<Playlist>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistAdapter.MyViewHolder {
        val view =
            LayoutPlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position], position)

    }

    class MyViewHolder(view: LayoutPlaylistItemBinding) : RecyclerView.ViewHolder(view.root) {
        private val binding = view
        fun bind(data: Playlist, position: Int) {
            binding.textView.text = "${data.playlistId}: ${data.playlistName}"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}