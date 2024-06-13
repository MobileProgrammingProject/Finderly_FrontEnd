package com.example.finderly.screen.userScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.viewModel.UserViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

@Composable
fun SplashScreen(navController: NavHostController) {
    val userViewModel : UserViewModel = viewModel()
    val context = LocalContext.current

    var userID by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
    }
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Toast.makeText(context, "카카오 로그인 실패", Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            // 로그인 성공 시 사용자 정보 요청
            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Toast.makeText(context, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                } else if (user != null) {
                    // 카카오 로그인 성공 시 가져온 nickname과 ID 토큰을 사용
                    userID = user.id.toString()
                    userPassWord = "0000"
                    Log.d("KakaoID",userID)
                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()

                    // 로그인 함수 호출
                    userViewModel.login(userID, userPassWord)
                    editor.putString("userId", userID)
                    editor.apply()
                    navController.navigate("Search")
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Finderly",
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = R.color.green)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.finderly_logo),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(70.dp))
        Button(
            onClick = { navController.navigate("Login") },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.gray)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(200.dp, 45.dp)
        ) {
            Text(
                text = "아이디 로그인",
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                // 카카오 로그인 구현
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    UserApiClient.instance.loginWithKakaoTalk(context, callback = kakaoLoginCallback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoLoginCallback)
                }
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.yellow)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(200.dp, 45.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.kakao_logo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "카카오 로그인",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "회원가입",
            color = colorResource(id = R.color.text_gray),
            fontSize = 16.sp,
            fontWeight = FontWeight.Thin,
            modifier = Modifier
                .clickable { navController.navigate("Register") }
        )
    }
}