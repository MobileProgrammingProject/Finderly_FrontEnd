package com.example.finderly.component

import android.content.Context

fun getUserId(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("userId", null)
}