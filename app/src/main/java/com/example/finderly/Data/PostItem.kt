package com.example.finderly.Data

data class PostListItem(
    var postTitle:String,
    var postContent:String,
    var postId:String
)

data class PostRequest (
    var userId:String,
    var postTitle:String,
    var postContent:String,
    var postCategory:Int,
    var secretCheck:Boolean,
    var pictures:List<String>
)

data class registerResponse (
    var message:String?,
    var postId:String?
)

data class Post(
    val postId: String, // 게시글 아이디
    val postTitle : String, // 제목
    val postContent: String, // 게시글 내용
    val pictures: List<String>, // 사진 url 리스트
    val commentsCnt: Int, // 댓글 수
    val comments: List<Comment> // 댓글 리스트
)

data class Response(
    val message: String
)