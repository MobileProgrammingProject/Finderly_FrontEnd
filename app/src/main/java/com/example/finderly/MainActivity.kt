package com.example.finderly

import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.compose.rememberNavController
import com.example.finderly.Navigate.NavGraph
import com.example.finderly.ui.theme.FinderlyTheme
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class MainActivity : ComponentActivity() {
    @OptIn(UnstableApi::class)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // log에 나온 keyhash 값 단톡에 남겨주세요! + 애뮬레이터 꼭 API 30 이하인 걸로 써주세요!
        Log.d(TAG, "keyhash : ${Utility.getKeyHash(this)}")

        // Kakao SDK 초기화
        KakaoSdk.init(this, "b63c9474cf80866f15fb0001087ca551")

        setContent {
            FinderlyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}