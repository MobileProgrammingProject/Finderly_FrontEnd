package com.example.finderly.screen.postScreen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.Data.PostRequest
import com.example.finderly.R
import com.example.finderly.component.BigRegisterButton
import com.example.finderly.component.CameraCaptureAndImagePicker
import com.example.finderly.component.CreateImage
import com.example.finderly.component.PostHeader
import com.example.finderly.component.getUserId
import com.example.finderly.viewModel.PostViewModel


@Composable
fun RegisterScreen(navHostController: NavHostController) {
    val postViewModel: PostViewModel = viewModel()
    val context = LocalContext.current
    val userId = getUserId(context).toString() // userId 저장
    var postId by rememberSaveable {
        mutableStateOf("")
    }

    var title by rememberSaveable {
        mutableStateOf("")
    }
    var titleHasFocus by rememberSaveable {
        mutableStateOf(false)
    }
    var expended by rememberSaveable {
        mutableStateOf(false)
    }
    val focusRequester = FocusRequester()
    val topPadding = 140.dp
    val verticalScrollState = rememberScrollState()

    var contentHasFocus by rememberSaveable {
        mutableStateOf(false)
    }
    var content by rememberSaveable {
        mutableStateOf("")
    }

    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    var postCategory by rememberSaveable {
        mutableIntStateOf(0)
    }

    // 사진 등록
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val imageUriList = remember {
        mutableStateListOf<Uri?>()
    }
    val imgScrollState = rememberScrollState()

    LaunchedEffect(postViewModel.success) {
        if(postViewModel.success == true){
            Toast.makeText(context, postViewModel.message, Toast.LENGTH_SHORT).show()
            navHostController.navigate("LostPost/$postCategory/${postId}") {
                popUpTo("RegisterPost") { inclusive = true }
            }
        }
        else if(postViewModel.success == false){
            Toast.makeText(context, postViewModel.message, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        // 헤더 컴포넌트
        PostHeader(if(postCategory==0){R.string.register_lost_post}else R.string.register_found_post)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .padding(top = topPadding)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(30.dp)
        ) {

            // 제목 입력
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 제목
                TextField(
                    value = title,
                    label = {
                        if (!titleHasFocus && title.isEmpty())
                            Text(
                                text = "제목",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.field_text_gray)
                            )
                    },
                    onValueChange = { title = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedIndicatorColor = Color.Gray
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            titleHasFocus = focusState.isFocused
                        },
                    textStyle = TextStyle(fontSize = 20.sp),
                    singleLine = true
                )

                // 메뉴 선택하면 어떤 게시판인지 표시하도록 수정
                Button(
                    onClick = { expended = true },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dropdown),
                        contentDescription = "drop down",
                        modifier = Modifier.size(24.dp)
                    )
                    DropdownMenu(
                        expanded = expended,
                        onDismissRequest = { expended = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(text = { Text(
                            text = "분실물 게시판",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        ) }, onClick = { expended = false
                            postCategory = 0 })
                        DropdownMenuItem(text = { Text(
                            text = "습득물 게시판",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        ) }, onClick = { expended = false
                            postCategory = 1 })
                    }
                }
            }


            // 내용 입력

            OutlinedTextField(
                value = content,
                label = {
                    if (!contentHasFocus)
                        Text(
                            text = "내용을 입력하세요.",
                            color = colorResource(id = R.color.field_text_gray),
                            fontSize = 15.sp
                        )
                },
                onValueChange = { content = it },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .heightIn(min = 100.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        contentHasFocus = focusState.isFocused
                    },
                textStyle = TextStyle(fontSize = 15.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray
                ),
                shape = RoundedCornerShape(8.dp)
            )

            // 익명 체크
            Row(
                modifier = Modifier.padding(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.white),
                        //checkedColor = Color.Gray,
                        uncheckedColor = colorResource(id = R.color.field_border_gray),
                        //uncheckedColor = colorResource(id = R.color.field_border_gray),
                        checkmarkColor = colorResource(id = R.color.green)
                    ),
                    modifier = Modifier
                        .padding(end = 15.dp, bottom = 20.dp, top = 20.dp, start = 10.dp)
                        .size(13.dp)
                )
                Text(
                    text = "익명",
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }

            Text(
                text = "사진 등록",
                color = Color.Gray,
                modifier = Modifier.padding(start = 5.dp)
            )

            Row (
                modifier = Modifier
                    .horizontalScroll(imgScrollState)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
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

            // 게시글 등록 버튼
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                BigRegisterButton("게시글 등록하기", navHostController) {
                    //"PostBoard"
                    //val pictures = mutableStateListOf<MultipartBody.Part>()
                    val pictures = imageUriList.map{uri -> uri.toString()}
//                    imageUriList.forEach{uri->
//                        val file = getFileFromUri(context, uri)
//                        file?.let {
//                            val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())
//                            val body = MultipartBody.Part.createFormData("images", it.name, requestFile)
//                            pictures.add(body)
//                        }
//                    }
                    val postRequest = PostRequest(
                        userId = userId,
                        postTitle = title,
                        postContent = content,
                        secretCheck = checked,
                        postCategory = postCategory,
                        pictures = pictures
                    )
                    Log.d("Image List", "$pictures")

                    postViewModel.registerPost(postRequest) { result ->

                        if (result.message == "Failed") {
                            // 실패 처리 로직
                        } else if (result.message == "Error") {
                            // 에러 처리 로직
                        } else {
                            // 성공 처리 로직
                            Log.d(
                                "[Register Post]",
                                "postCategory=$postCategory & postId=${result.postId}"
                            )
                            postId = result.postId?:""
                        }
                    }
                }
            }
        }
    }

}
