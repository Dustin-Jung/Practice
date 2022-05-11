package com.android.aop.part2.airbnbpractice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.airbnbpractice.databinding.ItemHouseInformationBinding
import com.android.aop.part2.airbnbpractice.ui.adapter.viewholder.HouseViewHolder

class HouseViewPagerAdapter : RecyclerView.Adapter<HouseViewHolder>() {

    val houseList = mutableListOf<HouseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {

        val binding =
            ItemHouseInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {

        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int {

        return houseList.size
    }

    fun addAll(list: List<HouseModel>) {
        houseList.addAll(list)
        notifyDataSetChanged()
    }
}
