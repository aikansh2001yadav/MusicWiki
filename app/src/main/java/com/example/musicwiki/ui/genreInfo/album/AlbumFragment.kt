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
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.databinding.AlbumFragmentBinding
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.utils.toast

class AlbumFragment : Fragment() {

//    private lateinit var appDatabase : AppDatabase
    private lateinit var myApi: MyApi
    private lateinit var genreRepository: GenreRepository
    private lateinit var albumViewModelFactory: AlbumViewModelFactory
    private lateinit var viewModel: AlbumViewModel

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

        myApi = MyApi()
//        appDatabase = AppDatabase.getInstance(requireContext())!!
        genreRepository = GenreRepository(myApi)
        albumViewModelFactory = AlbumViewModelFactory(genreRepository)

        viewModel = ViewModelProvider(this, albumViewModelFactory)[AlbumViewModel::class.java]

        val genreName = arguments?.getString("GENRE_NAME")
        if (genreName != null) {
            viewModel.getAlbumsList(genreName)
        }

        viewModel.getAlbumListLiveData().observe(viewLifecycleOwner) { albumList ->
            albumFragmentBinding!!.recyclerviewAlbums.also {
                it.layoutManager = GridLayoutManager(context, 2)
                it.adapter = AlbumsAdapter(albumList as ArrayList<Album>)
            }
        }

        viewModel.getMessageLiveData().observe(viewLifecycleOwner) {
            requireContext().toast(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        albumFragmentBinding = null
//        AppDatabase.destroyInstance()
    }
}