package com.example.finderly.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel

data class Post (var title:String, var content:String, val postId:String)

class LostPostViewModel(private val application:Application):AndroidViewModel(application = application){

    var lostPostList = mutableStateListOf<Post>()
        private set

    init{
        lostPostList.add(Post("에어팟", "에어팟 잃어버렸는데ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ", "1234"))
        lostPostList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        lostPostList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        lostPostList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        lostPostList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        lostPostList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
        lostPostList.add(Post("에어팟", "에어팟 잃어버렸는데", "1234"))
    }
}

class FoundPostViewModel(private val application:Application):AndroidViewModel(application = application){

    var foundPostList = mutableStateListOf<Post>()
        private set

    init{
        foundPostList.add(Post("에어팟", "에어팟 주웠는데", "1234"))
        foundPostList.add(Post("에어팟", "에어팟 주웠는데", "1234"))
        foundPostList.add(Post("에어팟", "에어팟 주웠는데", "1234"))
        foundPostList.add(Post("에어팟", "에어팟 주웠는데", "1234"))
        foundPostList.add(Post("에어팟", "에어팟 주웠는데", "1234"))
        foundPostList.add(Post("에어팟", "에어팟 주웠는데", "1234"))
        foundPostList.add(Post("에어팟", "에어팟 주웠는데", "1234"))
    }

}