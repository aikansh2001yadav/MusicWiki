package com.example.musicwiki.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Coroutines util functions
 */
object CoroutineExtensions {
    fun io(work: suspend () -> Unit) = CoroutineScope(Dispatchers.IO).launch {
        work.invoke()
    }

    fun main(work: suspend () -> Unit) = CoroutineScope(Dispatchers.Main).launch {
        work.invoke()
    }

    fun <T : Any> ioThenMain(work: suspend (() -> T), callback: (T) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val tempList = CoroutineScope(Dispatchers.IO).async io@{
                return@io work.invoke()
            }.await()
            callback(tempList)
        }
    }
}