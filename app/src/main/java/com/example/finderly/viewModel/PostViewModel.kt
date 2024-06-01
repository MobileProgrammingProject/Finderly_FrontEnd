package com.example.finderly.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel

data class Post (var title:String, var content:String, val postId:String)
class PostViewModel(private val application:Application):AndroidViewModel(application = application){

    var postList = mutableStateListOf<Post>()
        private set

    init{
        postList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        postList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        postList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        postList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        postList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        postList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        postList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
    }

}
