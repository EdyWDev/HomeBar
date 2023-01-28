package com.example.homebar.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("goneUnless")
fun View.goneUnless(visible: Boolean?) {
    visible?.let {
        this.visibility = if (visible) View.VISIBLE else View.GONE
    }
}