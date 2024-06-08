package com.example.finderly.api

import com.example.finderly.Data.SignUpRequest
import com.example.finderly.Data.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    // comment 댓글

    // lost 분실물

    // post 게시글

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

//    @GET("/user/logout") // 로그아웃
//    suspend fun getLogout(
//        @Query("userId") userId: String
//    ): UserResponse
}