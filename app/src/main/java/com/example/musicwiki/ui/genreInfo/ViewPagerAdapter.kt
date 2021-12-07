package com.example.musicwiki.ui.genreInfo

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicwiki.ui.genreInfo.album.AlbumFragment
import com.example.musicwiki.ui.genreInfo.artist.ArtistFragment
import com.example.musicwiki.ui.genreInfo.track.TrackFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle, private val genreName:String) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int) = when(position){
        0 -> AlbumFragment.newInstance(genreName)
        1 -> ArtistFragment()
        2 -> TrackFragment()
        else -> AlbumFragment()
    }
}