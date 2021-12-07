package com.example.musicwiki.utils

import java.io.IOException

object UtilExceptions {
    class NetworkException(message: String) : IOException(message)
}