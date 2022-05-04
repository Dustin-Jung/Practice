package com.android.aop.part2.airbnbpractice.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide
        .with(view)
        .load(url)
        .transform(CenterCrop())
        .into(view)
}