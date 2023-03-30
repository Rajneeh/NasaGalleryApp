package com.nasa.gallery.network

import com.nasa.gallery.model.ImageDataModel
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET("/obvious/take-home-exercise-data/trunk/nasa-pictures.json")
    suspend fun getImages(): Response<List<ImageDataModel>>
}