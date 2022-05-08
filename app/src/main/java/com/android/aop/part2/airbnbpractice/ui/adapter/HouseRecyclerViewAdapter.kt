package com.android.aop.part2.airbnbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.data.HouseModel
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseRecyclerviewBinding

class HouseRecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewHolder>() {

    private val houseList = mutableListOf<HouseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            ItemHouseRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int =
        houseList.size

    fun addAll(list: List<HouseModel>) {
        houseList.addAll(list)
        notifyDataSetChanged()
    }
}

class RecyclerViewHolder(private val binding: ItemHouseRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(houseModel: HouseModel) {
        binding.model = houseModel
    }
}