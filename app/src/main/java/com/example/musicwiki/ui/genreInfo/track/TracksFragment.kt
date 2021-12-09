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
import com.example.musicwiki.data.db.AppDatabase
import com.example.musicwiki.data.db.entities.Track
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.databinding.TrackFragmentBinding
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.NetworkConnectionInterceptor
import com.example.musicwiki.utils.toast

class TracksFragment : Fragment() {

    private var genreName: String? = ""

    private var myApi: MyApi? = null
    private var appDatabase: AppDatabase? = null
    private var genreRepository: GenreRepository? = null
    private var networkConnectionInterceptor: NetworkConnectionInterceptor? = null
    private var trackViewModelFactory: TrackViewModelFactory? = null
    private var viewModel: TrackViewModel? = null
    private var trackFragmentBinding: TrackFragmentBinding? = null

    companion object {
        fun newInstance(genreName: String) = TracksFragment().apply {
            arguments = Bundle().apply {
                putString("GENRE_NAME", genreName)
            }
        }
    }

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

        networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        myApi = MyApi(networkConnectionInterceptor!!)
        appDatabase = AppDatabase.getInstance(requireContext())!!
        genreRepository = GenreRepository(myApi!!, appDatabase!!)
        trackViewModelFactory = TrackViewModelFactory(genreRepository!!)
        viewModel = ViewModelProvider(this, trackViewModelFactory!!)[(TrackViewModel::class.java)]

        genreName = arguments?.getString("GENRE_NAME")

        viewModel!!.getTrackListLiveData().observe(viewLifecycleOwner) { trackList ->
            if (trackList != null) {
                trackFragmentBinding!!.recyclerviewTracks.also {
                    it.layoutManager = GridLayoutManager(context, 2)
                    it.adapter = TracksAdapter(trackList as ArrayList<Track>)
                }
            }
        }

        viewModel!!.getMessageLiveData().observe(viewLifecycleOwner) {
            requireContext().toast(it)
        }
    }

    override fun onResume() {
        super.onResume()
        if (genreName != null) {
            viewModel!!.getTracks(genreName!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppDatabase.destroyInstance()
        trackFragmentBinding = null
        viewModel = null
        trackViewModelFactory = null
        networkConnectionInterceptor = null
        genreRepository = null
        myApi = null
    }
}