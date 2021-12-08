package com.example.musicwiki.network

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

    @GET("/2.0/?method=tag.getTopTags&api_key=9d9a8fb5b29106cde78ac8e0b8bab6e1&format=json")
    suspend fun getGenreList(): Response<GenreItems>

    @GET("/2.0/?method=tag.getinfo&api_key=9d9a8fb5b29106cde78ac8e0b8bab6e1&format=json")
    suspend fun getGenreInfo(@Query("tag") tag: String): Response<GenreInfo>

    @GET("/2.0/?method=tag.gettopalbums&api_key=9d9a8fb5b29106cde78ac8e0b8bab6e1&limit=10&format=json")
    suspend fun getAlbumList(@Query("tag")tag:String) : Response<Albums>

    @GET("/2.0/?method=tag.gettopartists&api_key=9d9a8fb5b29106cde78ac8e0b8bab6e1&limit=10&format=json")
    suspend fun getArtistList(@Query("tag")tag:String) : Response<TopArtists>

    @GET("/2.0/?method=tag.gettoptracks&api_key=9d9a8fb5b29106cde78ac8e0b8bab6e1&limit=10&format=json")
    suspend fun getTrackList(@Query("tag")tag:String) : Response<Tracks>

    companion object {
        operator fun invoke(networkConnectionInterceptor : NetworkConnectionInterceptor): MyApi {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://ws.audioscrobbler.com").client(okHttpClient).build().create(MyApi::class.java)
        }
    }
}