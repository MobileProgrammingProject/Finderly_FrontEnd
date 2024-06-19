package com.example.finderly.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class ReportViewModel : ViewModel() {
    var success by mutableStateOf<Boolean?>(null)
    var message by mutableStateOf<String?>(null)

    fun initializeState() {
        success = null
        message = null
    }

    fun report(category: Int, id: String, userId: String){
        viewModelScope.launch{
            try {
                val response = RetrofitInstance.api.getReport(category, id, userId)
                if (response.message == "신고 완료") {
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
                Log.d("report", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }
}
