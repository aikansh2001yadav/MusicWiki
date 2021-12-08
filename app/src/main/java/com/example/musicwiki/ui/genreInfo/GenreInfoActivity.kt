package com.example.musicwiki.ui.genreInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.R
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.data.room.AppDatabase
import com.example.musicwiki.databinding.ActivityGenreInfoBinding
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.NetworkConnectionInterceptor
import com.example.musicwiki.utils.toast
import com.google.android.material.tabs.TabLayoutMediator

class GenreInfoActivity : AppCompatActivity() {
    private var genreName: String? = null
    private lateinit var myApi : MyApi
    private lateinit var appDatabase: AppDatabase
    private lateinit var genreRepository: GenreRepository
    private lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor
    private lateinit var genreInfoViewModelFactory: GenreInfoViewModelFactory
    private lateinit var genreInfoViewModel: GenreInfoViewModel
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var genreInfoBinding: ActivityGenreInfoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genreInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_genre_info)

        setupActionBarWithBack()

        networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        myApi = MyApi(networkConnectionInterceptor)
        appDatabase = AppDatabase.getInstance(this)!!
        genreRepository = GenreRepository(myApi, appDatabase)
        genreInfoViewModelFactory = GenreInfoViewModelFactory(genreRepository)
        genreInfoViewModel =
            ViewModelProvider(this, genreInfoViewModelFactory)[GenreInfoViewModel::class.java]

        genreName = intent.getStringExtra("GENRE_NAME")
        genreName = genreName?.uppercase()
        if (genreName != null) {
            genreInfoViewModel.getGenreInfo(genreName!!)
        }

        if (genreName != null) {
            setupViewPager()
        }

        genreInfoViewModel.getGenreInfoLiveData().observe(this) {
            if (it != null) {
                val index = it.wiki.summary.indexOf("<a")
                val summary = it.wiki.summary.substring(0, index)
                genreInfoBinding!!.textGenreTitle.text = it.name
                genreInfoBinding!!.textGenreSummary.text = summary
            }
        }

        genreInfoViewModel.getMessageLiveData().observe(this) {
            toast(it)
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle, genreName!!)
        genreInfoBinding!!.viewpager.adapter = viewPagerAdapter

        TabLayoutMediator(
            genreInfoBinding!!.tabLayout,
            genreInfoBinding!!.viewpager,
            true
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "ALBUMS"
                1 -> tab.text = "ARTISTS"
                2 -> tab.text = "TRACKS"
            }
        }.attach()
    }

    private fun setupActionBarWithBack() {
        setSupportActionBar(genreInfoBinding!!.toolbarGenreInfo)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)

        genreInfoBinding!!.toolbarGenreInfo.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onDestroy() {
        super.onDestroy()
        genreInfoBinding = null
        viewPagerAdapter = null
    }
}