package com.example.musicwiki.ui.genreInfo.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.data.db.entities.Artist
import com.example.musicwiki.databinding.ArtistItemBinding

class ArtistsAdapter(private val artistList: ArrayList<Artist>) :
    RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.artist_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.artistItemBinding.artist = artistList[position]
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    inner class ArtistViewHolder(val artistItemBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(artistItemBinding.root)
}