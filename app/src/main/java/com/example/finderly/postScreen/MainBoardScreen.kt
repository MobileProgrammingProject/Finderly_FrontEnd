package com.example.finderly.postScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.finderly.component.PostHeader

@Composable
fun MainBoardScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        // 헤더 박스
        Box(modifier = Modifier.height(190.dp)) {
            PostHeader(
                subTitle = R.string.main_post,
                backgroundColor = Color.White,
                title = R.color.green,
                subTitl = R.color.text_deepgreen
            )
            Text(
                text = "찾고 싶거나\n찾은 물건을\n등록해보세요",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 50.dp, start = 250.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.lightgreen)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 130.dp, start = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus_deepgreen),
                    contentDescription = "plus post",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 10.dp)
                )
                Text(
                    text = "게시글 등록하기",
                    color = colorResource(id = R.color.text_deepgreen),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        // 메뉴 탭
        var lostCheck by rememberSaveable {
            mutableStateOf(true)
        }
        var findCheck by rememberSaveable {
            mutableStateOf(false)
        }

        Column {
            Row {
                Column(
                    modifier = Modifier
                        .size(90.dp, 50.dp)
                        .clickable {
                            lostCheck = true
                            findCheck = false
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "분실물",
                        fontSize = 16.sp,
                        color = if (lostCheck) colorResource(id = R.color.text_deepgreen) else colorResource(id = R.color.text_gray)
                    )

                    if (lostCheck) {
                        Divider(
                            color = colorResource(id = R.color.text_deepgreen),
                            modifier = Modifier
                                .width(90.dp)
                                .height(2.dp)
                                .offset(y = 13.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .size(90.dp, 50.dp)
                        .clickable {
                            lostCheck = false
                            findCheck = true
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "습득물",
                        fontSize = 16.sp,
                        color = if (findCheck) colorResource(id = R.color.text_deepgreen) else colorResource(
                            id = R.color.text_gray
                        )
                    )

                    if (findCheck) {
                        Divider(
                            color = colorResource(id = R.color.text_deepgreen),
                            modifier = Modifier
                                .width(90.dp)
                                .height(2.dp)
                                .offset(y = 13.dp)
                        )
                    }
                }

            }
            Divider()
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.lightgreen))
        ) {
            
        }
    }
}