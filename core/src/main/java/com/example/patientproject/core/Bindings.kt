package com.example.patientproject.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(url: String?) {
    load(url)
}