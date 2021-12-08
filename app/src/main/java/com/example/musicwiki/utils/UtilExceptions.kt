package com.example.musicwiki.utils

import java.io.IOException

object UtilExceptions {
    class ApiException(message: String) : IOException(message)

    class NoConnectivityException : IOException() {
        override val message: String
            get() =
                "No network available, please check your WiFi or Data connection"
    }

    class NoInternetException() : IOException() {
        override val message: String
            get() =
                "No internet available, please check your connected WIFi or Data"
    }
}