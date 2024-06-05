package com.example.finderly.api

import retrofit.GsonConverterFactory
import retrofit.Retrofit

object RetrofitInstance {
    val api: APIService by lazy{
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}