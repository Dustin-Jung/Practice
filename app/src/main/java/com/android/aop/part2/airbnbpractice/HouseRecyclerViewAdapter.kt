package com.android.aop.part2.airbnbpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseInformationBinding
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseRecyclerviewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

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