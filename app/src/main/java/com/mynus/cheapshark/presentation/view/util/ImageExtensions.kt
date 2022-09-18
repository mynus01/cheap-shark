package com.mynus.cheapshark.presentation.view.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mynus.cheapshark.R

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_round_image)
        .error(R.drawable.ic_round_image_not_supported)
        .into(this)
}