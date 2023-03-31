package com.nasa.gallery.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.nasa.gallery.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImageUrl(imageView: ImageView, imageUrl: String) {
    Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(imageView)
}