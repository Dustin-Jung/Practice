package com.android.aop.part2.airbnbpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseInformationBinding
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseRecyclerviewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class HouseRecyclerViewAdapter(val houseList: List<HouseModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemHouseRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val binding = (holder as HouseViewHolder).binding

        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int {

        return houseList.size
    }
}

class RecyclerViewHolder(val binding: ItemHouseRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(houseModel: HouseModel){
        val priceTextView = binding.priceTextView
        val titleTextView = binding.titleTextView
        val thumbnailImageView = binding.thumbnailImageView

        titleTextView.text = houseModel.title
        priceTextView.text = houseModel.price

        Glide
            .with(thumbnailImageView.context)
            .load(houseModel.imgUrl)
            .transform(CenterCrop())
            .into(thumbnailImageView)
    }
}