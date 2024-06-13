package com.example.finderly.screen.userScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.viewModel.UserViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    value: String,
    onValueChange : (String)->Unit,
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        /*colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.field_border_gray),
            unfocusedBorderColor = colorResource(id = R.color.field_border_gray),
            focusedLabelColor = colorResource(id = R.color.field_text_gray),
            unfocusedLabelColor = colorResource(id = R.color.field_text_gray)
        ),*/
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 35.dp, end = 35.dp),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun LoginScreen(navController: NavHostController) {
    var userID by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
    }
    val userViewModel : UserViewModel = viewModel()
    val context = LocalContext.current

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
                    // 카카오 로그인 성공 시 가져온 ID 토큰을 사용
                    userID = user.id.toString()
                    userPassWord = "0000"
                    Log.d("KakaoID",userID)
                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()

                    // 로그인 함수 호출
                    userViewModel.login(userID, userPassWord)
                    editor.putString("userId", userID)
                    editor.apply()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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

        LoginTextField(
            value = userPassWord,
            onValueChange = {userPassWord = it},
            label = "비밀번호",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                userViewModel.initializeState()
                userViewModel.login(userID,userPassWord)
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.gray)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .padding(start = 35.dp, end = 35.dp)
        ) {
            Text(
                text = "로그인",
                color = Color.Black,
                fontSize = 16.sp
            )
        }
        LaunchedEffect(userViewModel.success) {
            if(userViewModel.success == true){
                editor.putString("userId", userID)
                editor.apply()
                Toast.makeText(context, userViewModel.message,Toast.LENGTH_SHORT).show()
                navController.navigate("Search")
            }
            else if(userViewModel.success == false){
                Toast.makeText(context, userViewModel.message, Toast.LENGTH_SHORT).show()
                Log.d("Login", "Login Failed")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Divider(
            color = colorResource(id = R.color.field_text_gray),
            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "소셜 로그인",
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
                    // 카카오 로그인 구현
                    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                        UserApiClient.instance.loginWithKakaoTalk(context, callback = kakaoLoginCallback)
                    } else {
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoLoginCallback)
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
        Spacer(modifier = Modifier.height(100.dp))

        Row (
            modifier= Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            Text(
                text = "회원가입",
                color = colorResource(id = R.color.text_gray),
                fontSize = 16.sp,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .clickable { navController.navigate("Register") }
            )
            Spacer(modifier = Modifier.width(25.dp))

            Text(
                text = "아이디 찾기",
                color = colorResource(id = R.color.text_gray),
                fontSize = 16.sp,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .clickable { navController.navigate("Register") }
            )
            Spacer(modifier = Modifier.width(25.dp))

            Text(
                text = "비밀번호 찾기",
                color = colorResource(id = R.color.text_gray),
                fontSize = 16.sp,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .clickable { navController.navigate("Register") }
            )
        }
    }
}