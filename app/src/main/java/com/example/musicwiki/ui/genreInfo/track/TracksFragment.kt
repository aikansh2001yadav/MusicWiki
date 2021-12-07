package com.example.musicwiki.ui.genreInfo.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.R
import com.example.musicwiki.data.model.tracks.Track
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.databinding.TrackFragmentBinding
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.utils.toast

class TracksFragment : Fragment() {

    companion object {
        fun newInstance(genreName: String) = TracksFragment().apply {
            arguments = Bundle().apply {
                putString("GENRE_NAME", genreName)
            }
        }
    }

    private lateinit var myApi: MyApi
    private lateinit var genreRepository: GenreRepository
    private lateinit var trackViewModelFactory: TrackViewModelFactory
    private lateinit var viewModel: TrackViewModel

    private var trackFragmentBinding: TrackFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trackFragmentBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.track_fragment, container, false)
        return trackFragmentBinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myApi = MyApi()
        genreRepository = GenreRepository(myApi)
        trackViewModelFactory = TrackViewModelFactory(genreRepository)
        viewModel = ViewModelProvider(this, trackViewModelFactory)[(TrackViewModel::class.java)]

        val genreName = arguments?.getString("GENRE_NAME")
        if (genreName != null) {
            viewModel.getTracks(genreName)
        }

        viewModel.getTrackListLiveData().observe(viewLifecycleOwner) { trackList ->
            if (trackList != null) {
                trackFragmentBinding!!.recyclerviewTracks.also {
                    it.layoutManager = GridLayoutManager(context, 2)
                    it.adapter = TracksAdapter(trackList as ArrayList<Track>)
                }
            }
        }

        viewModel.getMessageLiveData().observe(viewLifecycleOwner) {
            requireContext().toast(it)
        }
    }
}