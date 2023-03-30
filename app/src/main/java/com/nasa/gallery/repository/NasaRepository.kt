package com.nasa.gallery.repository

import com.nasa.gallery.NasaGalleryApplication
import com.nasa.gallery.model.ImageDataModel
import com.nasa.gallery.network.APIInterface
import com.nasa.gallery.utils.ApplicationError
import com.nasa.gallery.utils.Result
import com.nasa.gallery.utils.handleExceptionWithContext
import com.nasa.gallery.utils.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NasaRepository(private val apiInterface: APIInterface) {
    suspend fun getImages(): Result<List<ImageDataModel>> {
        return withContext(Dispatchers.IO) {
            try {
                apiInterface.getImages()
                    .handleResponse {
                        if (it.isNotEmpty()) {
                            Result.Success(data = it)
                        } else {
                            Result.Error(ApplicationError.ServerError(it.toString()))
                        }
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                e.handleExceptionWithContext(NasaGalleryApplication.applicationContext())
            }
        }
    }
}