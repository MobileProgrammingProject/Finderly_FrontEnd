package com.example.finderly.Data;

import com.google.gson.annotations.SerializedName;
data class LoginRequest(
    @SerializedName("userId") val userId : String, // 사용자 아이디
    @SerializedName("userPassword") val userPassword : String, // 사용자 비밀번호
)

data class LoginResponse(
    val message : String
)
