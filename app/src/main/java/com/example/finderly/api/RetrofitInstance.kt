package com.example.finderly.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val api: APIService by lazy{
        Retrofit.Builder()
            .baseUrl("http://15.164.125.250:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}