package com.example.finderly.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
fun RegisterLostItemScreen(navController: NavHostController){
    val scrollstate = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(start = 25.dp, top = 15.dp)) {
            Text(
                text = "Finderly",
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.green)
            )
            Text(
                text = "분실물 등록하기",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_deepgreen)
            )
            Spacer(modifier = Modifier.height(25.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
                .verticalScroll(scrollstate),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "습득물",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "습득물",
                onValueChange = {},
                modifier = Modifier
                    .width(360.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.LightGray, //Set the text color Here
                    unfocusedTextColor = Color.LightGray,
                    cursorColor = Color.LightGray,
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "습득 위치",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "습득 위치",
                onValueChange = {},
                modifier = Modifier
                    .width(360.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.LightGray, //Set the text color Here
                    unfocusedTextColor = Color.LightGray,
                    cursorColor = Color.LightGray,
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "습득 날짜",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "습득 날짜",
                onValueChange = {},
                modifier = Modifier
                    .width(360.dp)
                    .height(60.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        modifier = Modifier.width(24.dp).height(24.dp),
                        contentDescription = "calendar"
                    )
                },
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.LightGray, //Set the text color Here
                    unfocusedTextColor = Color.LightGray,
                    cursorColor = Color.LightGray,
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "보관 장소",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "보관 장소",
                onValueChange = {},
                modifier = Modifier
                    .width(360.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.LightGray, //Set the text color Here
                    unfocusedTextColor = Color.LightGray,
                    cursorColor = Color.LightGray,
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "상세 정보",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "상세 정보",
                onValueChange = {},
                modifier = Modifier
                    .width(360.dp)
                    .height(150.dp),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.LightGray, //Set the text color Here
                    unfocusedTextColor = Color.LightGray,
                    cursorColor = Color.LightGray,
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                ),
                maxLines = 10
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "사진 등록",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )
            // 사진등록하는 부분 추가 예정
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .width(360.dp)
                    .height(45.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green),
                    contentColor = colorResource(id = R.color.text_deepgreen)
                )
            ) {
                Text(
                    text = "분실물 등록하기",
                    fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}