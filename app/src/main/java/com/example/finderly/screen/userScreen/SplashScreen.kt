package com.example.finderly.userScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.R

@Composable
fun SplashScreen(navController: NavHostController) {
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
            onClick = { navController.navigate("Login") },
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