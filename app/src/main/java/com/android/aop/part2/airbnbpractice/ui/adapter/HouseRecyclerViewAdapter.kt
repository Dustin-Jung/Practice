package com.android.aop.part2.airbnbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.data.HouseModel
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseRecyclerviewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class HouseRecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewHolder>() {

    private val houseList = mutableListOf<HouseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val binding = ItemHouseRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int {

        return houseList.size
    }

    fun addAll(list: List<HouseModel>){
        houseList.addAll(list)
        notifyDataSetChanged()
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