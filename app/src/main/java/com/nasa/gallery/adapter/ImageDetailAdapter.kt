package com.nasa.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.databinding.LayoutImageDetailItemBinding
import com.nasa.gallery.model.ImageDataModel

class ImageDetailAdapter(private val dataList : List<ImageDataModel>) : RecyclerView.Adapter<ImageDetailAdapter.ImageDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailViewHolder {
        val binding = LayoutImageDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageDetailViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ImageDetailViewHolder, position: Int) {
        holder.binding.imageDataModel = dataList[position]
    }

    class ImageDetailViewHolder(val binding: LayoutImageDetailItemBinding) : RecyclerView.ViewHolder(binding.root)
}