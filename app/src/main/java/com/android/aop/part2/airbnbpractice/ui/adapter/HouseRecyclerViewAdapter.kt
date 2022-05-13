package com.android.aop.part2.airbnbpractice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.data.model.HouseItem
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseRecyclerviewBinding
import com.android.aop.part2.airbnbpractice.ui.adapter.viewholder.RecyclerViewHolder

class HouseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val houseList = mutableListOf<HouseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding =
            ItemHouseRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int {

        return houseList.size
    }

    fun addAll(list: List<HouseItem>) {
        houseList.addAll(list)
        notifyDataSetChanged()
    }
}