package com.android.aop.part2.airbnbpractice.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseInformationBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class HouseViewHolder(private val binding: ItemHouseInformationBinding) : RecyclerView.ViewHolder(binding.root){

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