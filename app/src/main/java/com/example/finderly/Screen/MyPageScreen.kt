package com.example.finderly.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.R

@Composable
fun MyPageScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()
    val mywrite = listOf("에어팟 찾아요 ㅠㅠ", "에어팟 찾아요 ㅠㅠ", "에어팟 찾아요 ㅠㅠ", "에어팟 찾아요 ㅠㅠ", "에어팟 찾아요 ㅠㅠ", "에어팟 찾아요 ㅠㅠ")
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Text(
            text = "마이페이지",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.size(75.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = "파인더리님",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable {
                    navController.navigate("Login")
                }
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "로그아웃",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "내가 쓴 글",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollstate)
        ) {

            mywrite.forEach{
                Text(
                    text = it,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
                )
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}