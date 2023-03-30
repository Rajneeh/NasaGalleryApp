package com.nasa.gallery.utils

import android.content.Context
import com.nasa.gallery.R
import java.io.IOException

class NoConnectivityException(private val context: Context) : IOException() {
    override val message: String
        get() = context.resources.getString(R.string.noInternetErrorMsg)
}