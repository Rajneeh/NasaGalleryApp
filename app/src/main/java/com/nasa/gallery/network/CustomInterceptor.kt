package com.nasa.gallery.network

import android.content.Context
import android.os.Build
import com.nasa.gallery.utils.NoConnectivityException
import com.nasa.gallery.utils.isConnectedToInternet
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !context.isConnectedToInternet())
            throw NoConnectivityException(context)
        var request = chain.request()
        val requestBuilder = request.newBuilder().method(request.method, request.body)
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}