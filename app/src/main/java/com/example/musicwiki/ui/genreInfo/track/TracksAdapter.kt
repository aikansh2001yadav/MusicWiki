package com.example.musicwiki.ui.genreInfo.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.data.room.entities.Track
import com.example.musicwiki.databinding.TrackItemBinding

class TracksAdapter(private val trackList : ArrayList<Track>) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.track_item, parent, false))
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.trackItemBinding.track = trackList[position]
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    inner class TrackViewHolder(val trackItemBinding: TrackItemBinding) : RecyclerView.ViewHolder(trackItemBinding.root)
}