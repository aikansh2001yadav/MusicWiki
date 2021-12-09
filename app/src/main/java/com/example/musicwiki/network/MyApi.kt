package com.example.musicwiki.network

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.model.albums.Albums
import com.example.musicwiki.data.model.artists.TopArtists
import com.example.musicwiki.data.model.genreInfo.GenreInfo
import com.example.musicwiki.data.model.genreItems.GenreItems
import com.example.musicwiki.data.model.tracks.Tracks
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("/2.0/?method=tag.getTopTags&format=json")
    suspend fun getGenreList(@Query("api_key")apiKey: String = BuildConfig.API_KEY): Response<GenreItems>

    @GET("/2.0/?method=tag.getinfo&format=json")
    suspend fun getGenreInfo(@Query("tag") tag: String, @Query("api_key")apiKey: String = BuildConfig.API_KEY): Response<GenreInfo>

    @GET("/2.0/?method=tag.gettopalbums&limit=10&format=json")
    suspend fun getAlbumList(@Query("tag")tag:String, @Query("api_key")apiKey: String = BuildConfig.API_KEY) : Response<Albums>

    @GET("/2.0/?method=tag.gettopartists&limit=10&format=json")
    suspend fun getArtistList(@Query("tag")tag:String, @Query("api_key")apiKey: String = BuildConfig.API_KEY) : Response<TopArtists>

    @GET("/2.0/?method=tag.gettoptracks&limit=10&format=json")
    suspend fun getTrackList(@Query("tag")tag:String, @Query("api_key")apiKey: String = BuildConfig.API_KEY) : Response<Tracks>

    companion object {
        operator fun invoke(networkConnectionInterceptor : NetworkConnectionInterceptor): MyApi {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://ws.audioscrobbler.com").client(okHttpClient).build().create(MyApi::class.java)
        }
    }
}