package com.example.finderly.Data

data class PostListItem(
    var postTitle:String,
    var postContent:String,
    var postId:String
)

data class Post (
    var userId:String,
    var postId:String,
    var postTitle:String,
    var postContent:String,
    var postCategory:Int,
    var secretCheck:Boolean,
    var pictures:ArrayList<String>
)