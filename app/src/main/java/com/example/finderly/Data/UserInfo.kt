package com.example.finderly.Data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val message : String
)

data class SignUpRequest(
    @SerializedName("userId") val userId : String, // 사용자 아이디
    @SerializedName("userPassword") val userPassword : String, // 사용자 비밀번호
    @SerializedName("nickName") val nickName : String // 사용자 닉네임
)

data class MyResponse(
    val userName: String,
    val reports: Int,
    val founds: List<LostInfo>,
    val lostPosts: List<PostInfo>, // 분실물 게시글 정보 리스트 추가
    val foundPosts: List<PostInfo> // 습득물 게시글 정보 리스트 추가
)

data class LostInfo(
    val lostId: String,
    val lostName: String,
    val lostLocation: String,
    val storage: String
)

// 게시글 정보를 위한 데이터 클래스
data class PostInfo(
    val postId: String,
    val postTitle: String,
    val postContent: String
)

// 분실물 상세 정보 조회
data class LostItemInfoResponse(
    val lostId: String,
    val userId: String,
    val lostName: String,
    val lostLocation: String,
    val lostDate: String,
    val storage: String,
    val description: String,
    val pictures: List<String>
)

data class PostItemInfoResponse(
    val postId: String, // 게시글 아이디
    val postTitle : String, // 제목
    val postContent: String, // 게시글 내용
    val pictures: List<String>, // 사진 url 리스트
    val commentsCnt: Int, // 댓글 수
    val comments: List<Comment> // 댓글 리스트
)

data class Comment(
    val commentId: String, // 댓글 아이디
    val userId: String, // 사용자 아이디
    val content: String, // 댓글 내용
    val secretCheck: Boolean, // 댓글 익명 여부
)
