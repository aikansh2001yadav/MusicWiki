package com.example.musicwiki.ui.genre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.R
import com.example.musicwiki.data.model.genreItems.Tag
import com.example.musicwiki.data.repository.GenreItemsRepository
import com.example.musicwiki.databinding.ActivityIntroBinding
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.utils.CoroutineExtensions.io
import com.example.musicwiki.utils.CoroutineExtensions.main
import com.example.musicwiki.utils.toast

class GenreActivity : AppCompatActivity() {

    private var expanded = false
    private lateinit var myApi: MyApi
    private lateinit var genreViewModelFactory: GenreViewModelFactory
    private lateinit var genreItemsRepository: GenreItemsRepository
    private lateinit var genreViewModel: GenreViewModel
    private var introBinding: ActivityIntroBinding? = null
    private lateinit var totalGenresList: ArrayList<Tag>
    private lateinit var topGenresList: ArrayList<Tag>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        myApi = MyApi()
        genreItemsRepository = GenreItemsRepository(myApi)
        genreViewModelFactory = GenreViewModelFactory(genreItemsRepository)
        genreViewModel = ViewModelProvider(this, genreViewModelFactory)[GenreViewModel::class.java]

        topGenresList = ArrayList()
        genreViewModel.getGenre()
        genreViewModel.getGenreLiveData().observe(this) {
            if (it != null) {
                totalGenresList = it as ArrayList<Tag>
                io {
                    if (it.isNotEmpty()) {
                        for (i in 0 until 10) {
                            topGenresList.add(it[i])
                        }
                    }
                }
                main {
                    introBinding!!.recyclerviewTopGenres.also { recyclerViewTopGenre ->
                        recyclerViewTopGenre.layoutManager =
                            GridLayoutManager(this@GenreActivity, 3)
                        recyclerViewTopGenre.adapter =
                            GenreAdapter(this@GenreActivity, topGenresList)
                    }
                }
            }
        }

        genreViewModel.getMessageLiveData().observe(this) {
            toast(it)
        }

        introBinding!!.btnDropDown.setOnClickListener {
            main {
                if (expanded) {
                    it.setBackgroundResource(R.drawable.ic_expand)
                    introBinding!!.recyclerviewTopGenres.adapter =
                        GenreAdapter(this@GenreActivity, topGenresList)
                    expanded = false
                } else {
                    it.setBackgroundResource(R.drawable.ic_collapse)
                    introBinding!!.recyclerviewTopGenres.adapter =
                        GenreAdapter(this@GenreActivity, totalGenresList)
                    expanded = true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        introBinding = null
        topGenresList.clear()
        totalGenresList.clear()
    }
}