package com.example.finderly.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.LoginRequest
import com.example.finderly.Data.SignUpRequest
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class UserViewModel : ViewModel() {
    var success by mutableStateOf<Boolean?>(null)
    var message by mutableStateOf<String?>(null)

    fun initializeState(){
        success = null
        message = null
    }

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

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun signup(userId: String, userPassword: String, nickName: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.postSignUp(SignUpRequest(userId,userPassword,nickName))
                if(response.message == "회원가입 성공") {
                    message = response.message
                    success = true
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val jsonObject = JSONObject(errorBody)
                val errorMessage = jsonObject.optString("message", "알 수 없는 오류가 발생했습니다.")
                message = errorMessage
                success = false
            } catch (e: Exception) {
                Log.d("SignUp", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }
}

