package com.nasa.gallery.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.databinding.LayoutGridImageItemBinding
import com.nasa.gallery.model.ImageDataModel

class GridAdapter(private val imageActionListener: ImageActionListener) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {
    private var dataList = ArrayList<ImageDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = LayoutGridImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ImageDataModel>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.binding.imageDataModel = dataList[position]

        holder.binding.root.setOnClickListener {
            imageActionListener.openImageDetails(position)
        }
    }

    class GridViewHolder(val binding: LayoutGridImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface ImageActionListener {
        fun openImageDetails(position: Int)
    }
}