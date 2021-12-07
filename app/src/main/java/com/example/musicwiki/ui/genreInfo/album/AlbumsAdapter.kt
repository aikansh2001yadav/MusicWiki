package com.example.musicwiki.ui.genreInfo.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.databinding.AlbumItemBinding

class AlbumsAdapter(private val albumList: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.album_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.albumItemBinding.album = albumList[position]
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    inner class AlbumViewHolder(val albumItemBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(albumItemBinding.root)
}