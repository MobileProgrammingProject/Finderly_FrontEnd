package com.example.finderly.api

import com.example.finderly.Data.LoginRequest
import com.example.finderly.Data.SignUpRequest
import com.example.finderly.Data.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface APIService {
    // comment 댓글

    // lost 분실물

    // post 게시글

    // report 신고

    // user 사용자
    @PUT("/user/login") // 로그인
    suspend fun getLogin(
        @Body LoginRequest: LoginRequest
    ): UserResponse

    @POST("/user/signup") // 회원가입
    suspend fun postSignUp(
        @Body SignUpRequest: SignUpRequest
    ) : UserResponse
}