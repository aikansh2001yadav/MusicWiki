package com.example.musicwiki.network

import com.example.musicwiki.utils.UtilExceptions
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(work: suspend (() -> Response<T>)): T {
        val response = work.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw UtilExceptions.NetworkException(response.message())
        }
    }
}