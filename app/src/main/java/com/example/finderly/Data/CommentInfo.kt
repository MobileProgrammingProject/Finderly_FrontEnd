package com.example.finderly.Data

data class CommentResponse(
    val message: String,
    val commentId: String
)

data class CommentRequest(
    val userId: String,
    val postId: String,
    val postCategory: Int,
    val content: String,
    val secretCheck: Boolean
)