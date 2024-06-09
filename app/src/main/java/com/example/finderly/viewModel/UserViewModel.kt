package com.example.finderly.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.LostItemInfoResponse
import com.example.finderly.Data.LostItemRequest
import com.example.finderly.Data.MyResponse
import com.example.finderly.Data.PostItemInfoResponse
import com.example.finderly.Data.SignUpRequest
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class UserViewModel : ViewModel() {
    var success by mutableStateOf<Boolean?>(null)
    var message by mutableStateOf<String?>(null)
    var profile by mutableStateOf<MyResponse?>(MyResponse(
        userName = "loading ...",
        reports = 0,
        founds = emptyList(),
        lostPosts = emptyList(),
        foundPosts = emptyList()
    ))
    var lostiteminfo by mutableStateOf<LostItemInfoResponse?>(LostItemInfoResponse(
        lostId = "loading ...",
        userId= "loading ...",
        lostName= "loading ...",
        lostLocation= "loading ...",
        lostDate= "loading ...",
        storage= "loading ...",
        description= "loading ...",
        pictures = emptyList()
    ))
    var postiteminfo by mutableStateOf<PostItemInfoResponse?>(
        PostItemInfoResponse(
            "loading ...","loading ...","loading ...", emptyList(),0, emptyList()
    ))

    fun initializeState(){
        success = null
        message = null
        profile = MyResponse(
            userName = "loading ...",
            reports = 0,
            founds = emptyList(),
            lostPosts = emptyList(),
            foundPosts = emptyList()
        )
        lostiteminfo = LostItemInfoResponse(
            lostId = "loading ...",
            userId= "loading ...",
            lostName= "loading ...",
            lostLocation= "loading ...",
            lostDate= "loading ...",
            storage= "loading ...",
            description= "loading ...",
            pictures = emptyList()
        )
        postiteminfo = PostItemInfoResponse(
            "loading ...","loading ...","loading ...", emptyList(),0, emptyList()
        )
    }

    fun login(userId: String, userPassword: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLogin(userId, userPassword)
                if (response.message == "로그인 성공") {
                    message = response.message
                    success = true
                } else {
                    message = response.message
                    success = false
                }
            }catch (e: HttpException) { // HttpException 처리 추가
                val errorBody = e.response()?.errorBody()?.string()
                try {
                    val jsonObject = JSONObject(errorBody)
                    val errorMessage = jsonObject.optString("message", "알 수 없는 오류가 발생했습니다.")
                    message = errorMessage
                } catch (jsonException: JSONException) {
                    // 오류 메시지가 JSON 형태가 아닌 경우 처리
                    message = "알 수 없는 오류가 발생했습니다."
                }
                success = false
            }
            catch (e: Exception) {
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

    fun userProfile(userId: String){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProfile(userId)
                profile = response
                success = true
            } catch (e: Exception) {
                Log.d("userProfile", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }

    fun lostitemInfo(lostId: String){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLostItemInfo(lostId)
                lostiteminfo = response
                message = "상세정보 불러오기 성공"
                success = true
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val jsonObject = JSONObject(errorBody)
                val errorMessage = jsonObject.optString("message", "알 수 없는 오류가 발생했습니다.")
                message = errorMessage
                success = false
            } catch (e: Exception) {
                Log.d("lostitemInfo", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }

    fun postitemInfo(postCategory: Int, postId: String){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPostItemInfo(postCategory, postId)
                postiteminfo = response
                message = "상세정보 불러오기 성공"
                success = true
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val jsonObject = JSONObject(errorBody)
                val errorMessage = jsonObject.optString("message", "알 수 없는 오류가 발생했습니다.")
                message = errorMessage
                success = false
            } catch (e: Exception) {
                Log.d("lostitemInfo", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }
//    fun logout(userId: String){
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.getLogout(userId)
//                if (response.message == "로그아웃 성공") {
//                    message = response.message
//                    success = true
//                } else {
//                    message = response.message
//                    success = false
//                }
//            }catch (e: HttpException) { // HttpException 처리 추가
//                val errorBody = e.response()?.errorBody()?.string()
//                try {
//                    val jsonObject = JSONObject(errorBody)
//                    val errorMessage = jsonObject.optString("message", "알 수 없는 오류가 발생했습니다.")
//                    message = errorMessage
//                } catch (jsonException: JSONException) {
//                    // 오류 메시지가 JSON 형태가 아닌 경우 처리
//                    message = "알 수 없는 오류가 발생했습니다."
//                }
//                success = false
//            }
//            catch (e: Exception) {
//                Log.d("Login", e.toString())
//                e.printStackTrace()
//                message = "서버 연결 실패"
//                success = false
//            }
//        }
//    }
}

