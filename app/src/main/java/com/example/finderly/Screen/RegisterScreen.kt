package com.example.finderly.Screen

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.BigRegisterButton

@Composable
fun RegisterScreen(navController: NavHostController) {
    var userID by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
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
                .padding(start = 45.dp, end = 35.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "사용 가능한 아이디입니다.",
                fontSize = 13.sp,
                color = colorResource(id = R.color.field_text_gray),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(100.dp, 35.dp)
            ) {
                Text(
                    text = "중복 확인",
                    color = Color.White,
                    fontSize = 13.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))

        LoginTextField(
            value = userPassWord,
            onValueChange = {userPassWord = it},
            label = "비밀번호",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.height(10.dp))

        LoginTextField(
            value = userID,
            onValueChange = {userID = it},
            label = "닉네임",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 45.dp, end = 35.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "사용 가능한 닉네임입니다.",
                fontSize = 13.sp,
                color = colorResource(id = R.color.field_text_gray),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(100.dp, 35.dp)
            ) {
                Text(
                    text = "중복 확인",
                    color = Color.White,
                    fontSize = 13.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(150.dp))

        Box(modifier = Modifier.padding(start = 35.dp, end=35.dp)){
            BigRegisterButton(text = "가입하기", navHostController = navController, screen = "Search")
        }
    }
}