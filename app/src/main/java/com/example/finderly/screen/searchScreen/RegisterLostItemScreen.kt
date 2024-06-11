package com.example.finderly.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.BigRegisterButton
import com.example.finderly.component.RegisterImage

@Composable
fun RegisterLostItemScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 30.dp, start = 40.dp, end = 40.dp)
            .fillMaxSize()
            .verticalScroll(scrollstate),
    ) {
        Column() {
            Text(
                text = "Finderly",
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.green)
            )
            Text(
                text = "분실물 등록하기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_deepgreen)
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldWithLabel("습득물", true)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithLabel("습득 위치", true)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithIcon("습득 날짜", true, R.drawable.calendar)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithLabel("보관 장소", true)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithLabel("상세 정보", false, minLines = 5, maxLines = 10)
            Spacer(modifier = Modifier.height(15.dp))

            // 사진 등록
            Text(
                text = "사진 등록",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.Start)
            )
            // 사진등록하는 부분 추가 예정
            RegisterImage()
            Spacer(modifier = Modifier.height(20.dp))

            // 등록 버튼
            BigRegisterButton("분실물 등록하기", navController){
                // "Search"
            }
            Spacer(modifier = Modifier.height(30.dp))
        }

    }
}

@Composable
fun TextFieldWithLabel(text: String, singleLine: Boolean, minLines: Int = 1, maxLines: Int = 1) {
    Column {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = text,
            textStyle = TextStyle(fontSize = 14.sp),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.LightGray, //Set the text color Here
                unfocusedTextColor = Color.LightGray,
                cursorColor = Color.LightGray,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
            ),
            singleLine = singleLine,
            minLines = minLines,
            maxLines = maxLines
        )
    }
}


@Composable
fun TextFieldWithIcon(text: String, singleLine: Boolean, icon: Int) {
    Column {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = text,
            textStyle = TextStyle(fontSize = 14.sp),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.LightGray, //Set the text color Here
                unfocusedTextColor = Color.LightGray,
                cursorColor = Color.LightGray,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
            ),
            singleLine = singleLine,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    contentDescription = "calendar"
                )
            },
        )
    }

}
