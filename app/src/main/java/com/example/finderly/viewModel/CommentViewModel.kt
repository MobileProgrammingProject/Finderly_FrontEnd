package com.example.finderly.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

data class Comment(val userId:String, var content:String)

class CommentViewModel (private val application: Application): AndroidViewModel(application = application) {
    var commentList = mutableListOf<Comment>()
        private set

    init {
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))
        commentList.add(Comment(userId = "1234", content = "에어팟 봤는데"))

    }
}