package com.example.musicwiki.ui.genreInfo.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.R
import com.example.musicwiki.data.model.artists.Artist
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.data.room.AppDatabase
import com.example.musicwiki.databinding.ArtistFragmentBinding
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.NetworkConnectionInterceptor
import com.example.musicwiki.utils.toast

class ArtistFragment : Fragment() {

    companion object {
        fun newInstance(genreName: String) = ArtistFragment().apply {
            arguments = Bundle().apply {
                putString("GENRE_NAME", genreName)
            }
        }
    }

    private lateinit var myApi: MyApi
    private lateinit var appDatabase: AppDatabase
    private lateinit var genreRepository: GenreRepository
    private lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor
    private lateinit var artistViewModelFactory: ArtistViewModelFactory

    private var artistFragmentBinding: ArtistFragmentBinding? = null

    private var viewModel: ArtistViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        artistFragmentBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.artist_fragment, container, false)
        return artistFragmentBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        myApi = MyApi(networkConnectionInterceptor)
        appDatabase = AppDatabase.getInstance(requireContext())!!
        genreRepository = GenreRepository(myApi, appDatabase)
        artistViewModelFactory = ArtistViewModelFactory(genreRepository)
        viewModel = ViewModelProvider(this, artistViewModelFactory)[(ArtistViewModel::class.java)]

        val genreName = arguments?.getString("GENRE_NAME")
        if (genreName != null) {
            viewModel!!.getArtistList(genreName)
        }

        viewModel!!.getArtistListLiveData().observe(viewLifecycleOwner) { artistList ->
            if (artistList != null) {
                artistFragmentBinding!!.recyclerviewArtists.also {
                    it.layoutManager = GridLayoutManager(context, 2)
                    it.adapter = ArtistsAdapter(artistList as ArrayList<Artist>)
                }
            }
        }

        viewModel!!.getMessageLiveData().observe(viewLifecycleOwner){
            requireContext().toast(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
        artistFragmentBinding = null
    }
}