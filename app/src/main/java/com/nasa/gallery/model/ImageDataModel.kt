package com.nasa.gallery.model

import com.google.gson.annotations.SerializedName

data class ImageDataModel(
    @SerializedName("copyright") val copyright: String,
    @SerializedName("explanation") val description: String,
    @SerializedName("hdurl") val hdUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)