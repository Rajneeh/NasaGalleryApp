package com.nasa.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nasa.gallery.network.APIRequestHandler
import com.nasa.gallery.repository.NasaRepository
import com.nasa.gallery.viewmodel.ImageViewModel
import com.nasa.gallery.viewmodel.ImageViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ImageViewModel
    private lateinit var repository: NasaRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = NasaRepository(APIRequestHandler.getAPIInterface(this))
        viewModel = ViewModelProvider(this@MainActivity, ImageViewModelFactory(repository))[ImageViewModel::class.java]
        viewModel.fetchImages()
    }
}