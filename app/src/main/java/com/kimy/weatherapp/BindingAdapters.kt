package com.kimy.weatherapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .noPlaceholder()
        .into(imageView)
}