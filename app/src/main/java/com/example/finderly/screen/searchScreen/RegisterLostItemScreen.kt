package com.example.finderly.screen.searchScreen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.BigRegisterButton
import com.example.finderly.component.CameraCaptureAndImagePicker
import com.example.finderly.component.CreateImage
import com.example.finderly.component.getUserId
import com.example.finderly.viewModel.LostViewModel

@Composable
fun RegisterLostItemScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()
    val lostViewModel : LostViewModel = viewModel()
    val context = LocalContext.current
    var userId = getUserId(context).toString() // userId 저장
    // 사진 등록
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val imageUriList = remember {
        mutableStateListOf<Uri?>()
    }
    val imgScrollState = rememberScrollState()

    var lostName by remember {
        mutableStateOf("")
    }
    var lostLocation by remember {
        mutableStateOf("")
    }
    var lostDate by remember {
        mutableStateOf("")
    }
    var storage by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
//    var pictures by remember {
//        mutableStateOf(mutableListOf<String>())
//    }
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
            TextFieldWithLabel("습득물", lostName, { lostName = it }, true, imeAction = ImeAction.Next)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithLabel("습득 위치", lostLocation, { lostLocation = it }, true, imeAction = ImeAction.Next)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithIcon("습득 날짜", lostDate, { lostDate = it }, true, R.drawable.calendar, imeAction = ImeAction.Next)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithLabel("보관 장소", storage, { storage = it }, true, imeAction = ImeAction.Next)
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldWithLabel("상세 정보", description, { description = it }, false, minLines = 5, maxLines = 10, imeAction = ImeAction.Done)
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
            //RegisterImage()
            Row (
                modifier = Modifier
                    .horizontalScroll(imgScrollState)
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
            ) {
                CameraCaptureAndImagePicker{
                    selectedImageUri = it
                    imageUriList.add(it)
                }
                Log.d("Image uri", "$selectedImageUri")

                imageUriList.forEach{uri->
                    CreateImage(image = uri, 150.dp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 등록 버튼
            BigRegisterButton("분실물 등록하기", navController){
                val pictures = imageUriList.map { uri -> uri.toString() }
                lostViewModel.initializeState()
                lostViewModel.lostRegister(userId, lostName, lostLocation, lostDate, storage, description, pictures)
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        LaunchedEffect(lostViewModel.success) {
            if(lostViewModel.success == true){
                Toast.makeText(context, lostViewModel.message, Toast.LENGTH_SHORT).show()
                navController.navigate("Search")
            }
            else if(lostViewModel.success == false){
                Toast.makeText(context, lostViewModel.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun TextFieldWithLabel(text: String, inputText: String, onInputTextChange: (String) -> Unit, singleLine: Boolean, minLines: Int = 1, maxLines: Int = 1, imeAction: ImeAction) {
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
            value = inputText,
            textStyle = TextStyle(fontSize = 14.sp),
            onValueChange = onInputTextChange,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.DarkGray, //Set the text color Here
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
            ),
            singleLine = singleLine,
            minLines = minLines,
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(imeAction = imeAction)
        )
    }
}


@Composable
fun TextFieldWithIcon(text: String, inputText: String, onInputTextChange: (String) -> Unit, singleLine: Boolean, icon: Int, imeAction:ImeAction) {
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
            value = inputText,
            textStyle = TextStyle(fontSize = 14.sp),
            onValueChange = onInputTextChange,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.DarkGray, //Set the text color Here
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
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
            keyboardOptions = KeyboardOptions(imeAction = imeAction)
        )
    }

}
