package com.example.musicwiki.network

import com.example.musicwiki.data.model.genreInfo.GenreInfo
import com.example.musicwiki.data.model.genreItems.GenreItems
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

    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://ws.audioscrobbler.com").build().create(MyApi::class.java)
        }
    }
}