package com.example.finderly.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.finderly.R

data class Img (val image:Int)

class ImageViewModel(private val application: Application): AndroidViewModel(application = application){
    var imageList = mutableListOf<Img>()
        private set

    init {
        imageList.add(Img(R.drawable.iphone1))
        imageList.add(Img(R.drawable.iphone1))
        imageList.add(Img(R.drawable.iphone1))
    }
}