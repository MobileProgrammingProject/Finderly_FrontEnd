package com.example.finderly.api

import com.example.finderly.Data.LostItemInfoResponse
import com.example.finderly.Data.MyResponse
import com.example.finderly.Data.PostItemInfoResponse
import com.example.finderly.Data.PostListItem
import com.example.finderly.Data.SignUpRequest
import com.example.finderly.Data.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    // comment 댓글

    // lost 분실물
    @GET("/lost/detail") // 분실물 상세 조회
    suspend fun getLostItemInfo(
        @Query("lostId") lostId: String
    ): LostItemInfoResponse

    // post 게시글
    @GET("/post/detail") // 게시글 상세 조회
    suspend fun getPostItemInfo(
        @Query("postCategory") postCategory: Int,
        @Query("postId") postId: String
    ): PostItemInfoResponse

    // 게시글 리스트
    @GET("/post")
    suspend fun getPostList(
        @Query("postCategory") postCategory: Int
    ):List<PostListItem>

    // report 신고

    // user 사용자
    @GET("/user/login") // 로그인
    suspend fun getLogin(
        @Query("userId") userId: String,
        @Query("userPassword") userPassword: String
    ): UserResponse

    @POST("/user/signup") // 회원가입
    suspend fun postSignUp(
        @Body SignUpRequest: SignUpRequest
    ) : UserResponse

    @GET("/user/profile") // 마이페이지 조회
    suspend fun getProfile(
        @Query("userId") userId: String
    ) : MyResponse

//    @GET("/user/logout") // 로그아웃
//    suspend fun getLogout(
//        @Query("userId") userId: String
//    ): UserResponse
}