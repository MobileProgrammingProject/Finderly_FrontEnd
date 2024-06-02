package com.example.finderly.postScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.CreateImage
import com.example.finderly.component.PostHeader


@Composable
fun RegisterSreen(navHostController: NavHostController) {
    Box {
        PostHeader(R.string.register_post)    // 헤더 컴포넌트

        val topPadding = 150.dp
        val verticalScrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(verticalScrollState)
                .padding(top = topPadding)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .fillMaxSize()
                .background(Color.White)
                .padding(30.dp)
        ) {

            // 제목 입력
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 제목
                TextField(
                    value = title,
                    label = {
                        if (!titleHasFocus)
                            androidx.compose.material3.Text(
                                text = "제목",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.field_text_gray)
                            )
                    },
                    onValueChange = { title = it },
//                modifier = Modifier
//                    .background(color = Color.White)
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
                    textStyle = TextStyle(fontSize = 25.sp),
                    singleLine = true
                )

                // 메뉴 선택하면 어떤 게시판인지 표시
                Button(
                    onClick = { expended = true },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    //modifier = Modifier.padding(0.dp),
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
                        DropdownMenuItem(
                            onClick = { expended = false },
                        ) {
                            Text(
                                text = "분실물 게시판",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                        DropdownMenuItem(onClick = { expended = false }) {
                            Text(
                                text = "습득물 게시판",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }


            // 내용 입력
            var contentHasFocus by rememberSaveable {
                mutableStateOf(false)
            }
            var content by rememberSaveable {
                mutableStateOf("")
            }

            OutlinedTextField(
                value = content,
                label = {
                    if (!contentHasFocus)
                        androidx.compose.material3.Text(
                            text = "내용을 입력하세요.",
                            color = colorResource(id = R.color.field_text_gray),
                            fontSize = 15.sp
                        )
                },
                onValueChange = { content = it },
                modifier = Modifier
                    //.weight(1f)
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
            var checked by rememberSaveable {
                mutableStateOf(false)
            }

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
                androidx.compose.material3.Text(
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

            // 사진
            var showDialog by rememberSaveable {
                mutableStateOf(false)
            }
            val imgScrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .horizontalScroll(imgScrollState),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Lazy 컬럼으로 바꾸기
                Box(
                    // Dialog 추가
                    modifier = Modifier
                        .clickable { }
                ) {
                    CreateImage(image = R.drawable.plus_gray, 150.dp, 80.dp, color = Color.Gray)
                }
                CreateImage(image = R.drawable.iphone1, containerSize = 150.dp)
            }


            // 등록 버튼
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material3.Button(
                    onClick = {
                        navHostController.navigate("PostBoard")
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(30.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "게시글 등록하기",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }


        }
    }
}
