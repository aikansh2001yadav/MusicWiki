package com.example.musicwiki.ui.genreInfo.album

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
import com.example.musicwiki.data.db.entities.Album
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.databinding.AlbumFragmentBinding
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.NetworkConnectionInterceptor
import com.example.musicwiki.utils.toast

class AlbumFragment : Fragment() {

    private var genreName:String? = ""
    private var appDatabase : AppDatabase? = null
    private var myApi: MyApi? = null
    private var genreRepository: GenreRepository? = null
    private var networkConnectionInterceptor: NetworkConnectionInterceptor? = null
    private var albumViewModelFactory: AlbumViewModelFactory? = null
    private var viewModel: AlbumViewModel? = null

    private var albumFragmentBinding : AlbumFragmentBinding? = null
    companion object {
        fun newInstance(genreName: String) = AlbumFragment().apply {
            arguments = Bundle().apply {
                putString("GENRE_NAME", genreName)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        albumFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.album_fragment, container, false)
        return albumFragmentBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        myApi = MyApi(networkConnectionInterceptor!!)
        appDatabase = AppDatabase.getInstance(requireContext())!!
        genreRepository = GenreRepository(myApi!!, appDatabase!!)
        albumViewModelFactory = AlbumViewModelFactory(genreRepository!!)

        viewModel = ViewModelProvider(this, albumViewModelFactory!!)[AlbumViewModel::class.java]

        genreName = arguments?.getString("GENRE_NAME")

        viewModel!!.getAlbumListLiveData().observe(viewLifecycleOwner) { albumList ->
            if (albumList != null) {
                albumFragmentBinding!!.recyclerviewAlbums.also {
                    it.layoutManager = GridLayoutManager(context, 2)
                    it.adapter = AlbumsAdapter(albumList as ArrayList<Album>)
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
            viewModel!!.getAlbumsList(genreName!!)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        albumFragmentBinding = null
        AppDatabase.destroyInstance()
        viewModel = null
        albumViewModelFactory = null
        networkConnectionInterceptor = null
        genreRepository = null
        myApi = null
    }
}