package com.nasa.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nasa.gallery.adapter.GridAdapter
import com.nasa.gallery.databinding.FragmentImageGridBinding
import com.nasa.gallery.utils.SpaceDecoration
import com.nasa.gallery.utils.autoFitColumns
import com.nasa.gallery.viewmodel.ImageViewModel

class ImageGridFragment : Fragment(), GridAdapter.ImageActionListener {
    private lateinit var gridAdapter: GridAdapter
    private lateinit var binding: FragmentImageGridBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[ImageViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImageGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridAdapter = GridAdapter(this)
        binding.recyclerView.apply {
            autoFitColumns(120)
            layoutManager = GridLayoutManager(this.context, 3)
            addItemDecoration(SpaceDecoration(16, true))
            adapter = gridAdapter
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (!it)
                binding.loader.visibility = View.GONE
        }

        viewModel.imageListLiveData.observe(viewLifecycleOwner) {
            gridAdapter.setData(it)
        }

        viewModel.errorMsgLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun openImageDetails(position: Int) {
        val action = ImageGridFragmentDirections.actionImageGridFragmentToImageDetailFragment(position)
        findNavController().navigate(action)
    }
}