package com.example.finderly.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.LostItem
import com.example.finderly.Data.LostItemRequest
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class LostViewModel: ViewModel() {

    var success by mutableStateOf<Boolean?>(null)
    var message by mutableStateOf<String?>(null)
    var lostItemList = mutableStateListOf<LostItem>()

    fun initializeState(){
        success = null
        message = null
    }

    fun lostRegister(
        userId: String,
        lostName: String,
        lostLocation: String,
        lostDate: String,
        storage: String,
        description: String,
        pictures: List<String>
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.postLostItem(
                    LostItemRequest(
                        userId,
                        lostName,
                        lostLocation,
                        lostDate,
                        storage,
                        description,
                        pictures
                    )
                )
                if (response.message == "분실물 등록 완료") {
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

    fun lostList() {
        viewModelScope.launch {
            try {
                val itemList = RetrofitInstance.api.getLostItemList()
                lostItemList.clear()
                lostItemList.addAll(itemList)
                success = true
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

    fun lostDelete(lostId:String){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.deleteLostItem(lostId)
                if (response.message == "분실물 삭제 완료") {
                    message = response.message
                    success = true
                } else {
                    message = response.message
                    success = false
                }
            }catch (e: HttpException) {
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
                Log.d("delete", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }

    fun lostSearch(search:String){
        viewModelScope.launch {
            try {
                val itemList = RetrofitInstance.api.getLostItemListSearch(search)
                lostItemList.clear()
                lostItemList.addAll(itemList)
                success = true
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                try {
                    val jsonObject = JSONObject(errorBody)
                    val errorMessage = jsonObject.optString("message", "알 수 없는 오류가 발생했습니다.")
                    message = errorMessage
                } catch (jsonException: JSONException) {
                    // 오류 메시지가 JSON 형태가 아닌 경우 처리
                    message = "알 수 없는 오류가 발생했습니다."
                }
                lostItemList.clear()
                success = false
            } catch (e: Exception) {
                Log.d("lostSearch", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                lostItemList.clear()
                success = false
            }
        }
    }

}
