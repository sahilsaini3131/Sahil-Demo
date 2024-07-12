package com.sahil.demo.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sahil.demo.databinding.HomeBinding
import com.sahil.demo.databinding.TextitemBinding
import com.sahil.demo.dataclass.DATA
import com.sahil.demo.views.home.HomeVM

class SimpleAdapter : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
//    private val userList by lazy { ArrayList<HomeVM.UserDataDC>() }
    private val userList by lazy { ArrayList<DATA.Data>() }

    inner class ViewHolder(var binding: TextitemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userDataDC: DATA.Data) {
            binding.tv.text = userDataDC.employeeName ?: ""

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(TextitemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

    fun addItems(list: List<DATA.Data>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }
}