package com.android.aop.part2.airbnbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.data.HouseModel
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseInformationBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class HouseViewPagerAdapter :
    RecyclerView.Adapter<HouseViewHolder>() {

    private val houseList = mutableListOf<HouseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder =
        HouseViewHolder(
            ItemHouseInformationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int = houseList.size

    fun addAll(list: List<HouseModel>) {
        houseList.addAll(list)
        notifyDataSetChanged()
    }
}

class HouseViewHolder(private val binding: ItemHouseInformationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(houseModel: HouseModel) {
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