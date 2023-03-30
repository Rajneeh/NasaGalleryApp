package com.nasa.gallery.utils

sealed class Result<out R> {
    data class Success<out T>(val data: T, var message: String? = null) : Result<T>()
    data class Error(val exception: ApplicationError) : Result<Nothing>()
}

sealed class ApplicationError(override val message: String, val code: Int) : Throwable(message) {
    data class ServerError(override val message: String, val errorCode: Int = 0) :
        ApplicationError(message, errorCode)

    data class SocketTimeOutError(override val message: String) : ApplicationError(message, 0)
    data class UnExpectedError(override val message: String) : ApplicationError(message, 0)
    data class NoConnectionError(override val message: String) : ApplicationError(message, 0)
}