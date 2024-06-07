package com.example.finderly.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.LoginRequest
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var success by mutableStateOf<Boolean?>(null)
    var message by mutableStateOf<String?>(null)

    fun login(userId: String, userPassword: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLogin(LoginRequest(userId, userPassword))
                if (response.message == "로그인 성공") {
                    message = response.message
                    success = true
                } else {
                    message = response.message
                    success = false
                }
            } catch (e: Exception) {
                Log.d("Login", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }
}

