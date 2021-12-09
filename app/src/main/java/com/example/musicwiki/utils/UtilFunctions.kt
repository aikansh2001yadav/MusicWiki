package com.example.musicwiki.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.musicwiki.R

/**
 * Show toast
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Shows image using glide
 */
@BindingAdapter("image")
fun image(imageView: View, text: String) {
    if (text.isEmpty()) {
        Glide.with(imageView).load(R.drawable.img_album).into(imageView as ImageView)
    } else {
        Glide.with(imageView).load(text).into(imageView as ImageView)
    }
}
