package com.showmiso.databindingproject

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

// requireAll attribute 가 여러개 일때 모두 정의해야 하는가?
@BindingAdapter("loadUrl", "placeHolder", requireAll = false)
fun ImageView.loadUrl(url: String, placeHolder: Drawable?) {
//    Glide.with(this)
//        .load(url)
//        .placeHolder(placeHolder)
//        .into(this)
}
