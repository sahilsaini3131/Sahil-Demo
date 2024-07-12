package com.sahil.demo.views.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sahil.demo.databinding.ItemImageBinding
import com.sahil.demo.dataclass.DATA
import com.sahil.demo.views.utils.Data

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    val image by lazy { ArrayList<Data>() }

    inner class ViewHolder(var binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindImage(imageUri: Uri?) {
            binding.image.setImageURI(imageUri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindImage(image[position].image)

    }

    override fun getItemCount(): Int {
        return image.size
    }

    fun addItems(list: List<Data>) {
//        image.clear()
        image.addAll(list)
        notifyDataSetChanged()   }
}



