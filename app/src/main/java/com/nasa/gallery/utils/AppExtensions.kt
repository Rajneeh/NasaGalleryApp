package com.nasa.gallery.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.R
import retrofit2.Response
import java.net.SocketTimeoutException

@RequiresApi(Build.VERSION_CODES.M)
fun Context.isConnectedToInternet(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    return false
}

fun <T, R> Response<T>.handleResponse(onCallSuccess: (T) -> Result<R>): Result<R> {
    return if (isSuccessful) {
        body()?.let {
            onCallSuccess(it)
        } ?: Result.Error(ApplicationError.UnExpectedError("Something Went Wrong!!"))
    } else {
        val errorMsg = "Something Went Wrong!!"
        Result.Error(ApplicationError.UnExpectedError(errorMsg))
    }
}

fun Exception.handleExceptionWithContext(context: Context): Result.Error {
    return when (this) {
        is SocketTimeoutException -> Result.Error(
            ApplicationError.SocketTimeOutError(
                message = context.resources.getString(
                    R.string.failedToConnectToServerMsg
                )
            )
        )
        else -> Result.Error(
            ApplicationError.UnExpectedError(
                message = message ?: context.resources.getString(R.string.somethingWentWrongMsg)
            )
        )
    }
}

fun RecyclerView.autoFitColumns(columnWidth: Int) {
    val displayMetrics = this.context.resources.displayMetrics
    val noOfColumns = ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
    this.layoutManager = GridLayoutManager(this.context, noOfColumns)
}