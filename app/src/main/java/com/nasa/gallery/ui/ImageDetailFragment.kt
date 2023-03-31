package com.nasa.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.nasa.gallery.adapter.ImageDetailAdapter
import com.nasa.gallery.databinding.FragmentImageDetailBinding
import com.nasa.gallery.viewmodel.ImageViewModel

class ImageDetailFragment : Fragment() {
    private var currentPage: Int = 0
    private var lastPageIndex: Int = 0
    private lateinit var binding: FragmentImageDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[ImageViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentPage = arguments?.getInt("imagePosition") ?: 0

        viewModel.imageListLiveData.value?.let {
            lastPageIndex = it.size - 1

            val imageDetailAdapter = ImageDetailAdapter(it)
            binding.viewPager.adapter = imageDetailAdapter
            binding.viewPager.currentItem = currentPage
        }

        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)

        binding.btnPrevious.setOnClickListener {
            binding.viewPager.currentItem = currentPage - 1
        }

        binding.btnNext.setOnClickListener {
            binding.viewPager.currentItem = currentPage + 1
        }
    }

    private val pageChangeCallback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            currentPage = position
            when (position) {
                0 -> {
                    binding.btnPrevious.visibility = View.GONE
                }
                lastPageIndex -> {
                    binding.btnNext.visibility = View.GONE
                }
                else -> {
                    binding.btnPrevious.visibility = View.VISIBLE
                    binding.btnNext.visibility = View.VISIBLE
                }
            }
        }
    }
}