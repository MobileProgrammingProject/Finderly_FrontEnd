package com.example.finderly.screen.userScreen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.BigRegisterButton
import com.example.finderly.viewModel.UserViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SignUpScreen(navController: NavHostController) {
    var userID by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
    }
    var nickname by remember {
        mutableStateOf("")
    }
    var idText by remember {
        mutableStateOf("사용 가능한 아이디입니다.")
    }
    var nicknameText by remember {
        mutableStateOf("사용 가능한 닉네임입니다.")
    }
    val userViewModel : UserViewModel = viewModel()
    val context = LocalContext.current

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
                    nickname = user.kakaoAccount?.profile?.nickname ?: ""
                    userPassWord = "0000"
                    Log.d("KakaoID",userID)
                    Log.d("KakaonNickName",nickname)

                    // 회원가입 함수 호출
                    userViewModel.initializeState()
                    userViewModel.signup(userID, userPassWord, nickname)
                    navController.navigate("Login")
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Finderly",
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = R.color.green)
        )
        Spacer(modifier = Modifier.height(30.dp))

        LoginTextField(
            value = userID,
            onValueChange = {userID = it},
            label = "아이디",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 45.dp)
        ){
            Text(
                text = idText,
                fontSize = 13.sp,
                color = colorResource(id = R.color.field_text_gray),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        LoginTextField(
            value = userPassWord,
            onValueChange = {userPassWord = it},
            label = "비밀번호",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))

        LoginTextField(
            value = nickname,
            onValueChange = {nickname = it},
            label = "닉네임",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 45.dp)
        ){
            Text(
                text = nicknameText,
                fontSize = 13.sp,
                color = colorResource(id = R.color.field_text_gray),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))

        Divider(
            color = colorResource(id = R.color.field_text_gray),
            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "소셜 회원가입",
            color = colorResource(id = R.color.text_gray)
        )
        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = colorResource(id = R.color.yellow),
                    shape = CircleShape
                )
                .clickable {
                    // 카카오 회원가입 구현
                    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                        UserApiClient.instance.loginWithKakaoTalk(
                            context,
                            callback = kakaoLoginCallback
                        )
                    } else {
                        UserApiClient.instance.loginWithKakaoAccount(
                            context,
                            callback = kakaoLoginCallback
                        )
                    }
                }
        ){
            Image(
                painter = painterResource(id = R.drawable.kakao_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(80.dp))

        Box(modifier = Modifier.padding(start = 35.dp, end=35.dp)){
            BigRegisterButton(text = "가입하기", navHostController = navController){
                userViewModel.initializeState()
                userViewModel.signup(userID,userPassWord,nickname)
            }
        }

        LaunchedEffect(userViewModel.success) {
            if(userViewModel.success == true){
                Toast.makeText(context, "$nickname 님"+userViewModel.message, Toast.LENGTH_SHORT).show()
                navController.navigate("Login")
            }
            else if(userViewModel.success == false){
                Toast.makeText(context, userViewModel.message, Toast.LENGTH_SHORT).show()
                if(userViewModel.message == "이미 존재하는 닉네임입니다.")
                    nicknameText = "이미 존재하는 닉네임입니다."
                else if(userViewModel.message == "이미 존재하는 아이디입니다.")
                    idText = "이미 존재하는 아이디입니다."
                else
                    Log.d("SignUp", "SignUp Failed")
            }
        }
    }
}