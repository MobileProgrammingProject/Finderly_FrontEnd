package com.example.finderly.postScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.CreateImage
import com.example.finderly.component.PostHeader


@Composable
fun PostScreen(navHostController: NavHostController, postType:Int){
    Box{
        PostHeader(postType)    // 헤더 컴포넌트
        
        // 좋아요 수
        var likeCounter by rememberSaveable {
            mutableStateOf(0)
        }
        // 댓글 수
        var commentsCounter by rememberSaveable {
            mutableStateOf(0)
        }

        val imgScrollState = rememberScrollState()
        val commentsScrollState = rememberScrollState()
        val topPadding = 150.dp

        Box(
            modifier = Modifier
                .padding(top = topPadding)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .fillMaxSize()
                .background(Color.White)
                .padding(30.dp)
        ){

            var expended by rememberSaveable {
                mutableStateOf(false)
            }

            Box (
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.TopEnd)
            ) {
                Image(painter = painterResource(
                    id = R.drawable.menu),
                    contentDescription = "menu",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {expended = true}
                )
                DropdownMenu(
                    expanded = expended,
                    onDismissRequest = { expended = false },
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    DropdownMenuItem(
                        onClick = { expended = false },
                        modifier = Modifier
                            .size(90.dp, 20.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            androidx.compose.material3.Text(
                                text = "신고하기",
                                fontSize = 12.sp,
                                color = Color.Gray,
                            )
                        }
                    }
                }
            }

            Column {

                // 타이틀
                Text(
                    text = "에어팟",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // 내용
                Text(
                    text = "건대입구에서\n에어팟 잃어버렸는데..",
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.padding(20.dp))

                // 사진
                Row (
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.horizontalScroll(imgScrollState)
                ) {
                    CreateImage(R.drawable.iphone1, 180.dp, 180.dp, Color.Transparent)
                    CreateImage(R.drawable.iphone1, 180.dp, 180.dp, Color.Transparent)
                }

                // 좋아요 & 댓글 아이콘
                Row (
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                ) {
                    // 좋아요 아이콘
                    Row (
                        //modifier = Modifier.align(Alignment.Bottom)
                        verticalAlignment = Alignment.Bottom
                    ) {
                        CreateIcon(Icons.Filled.FavoriteBorder){ likeCounter++ }
                        Text(
                            text = "$likeCounter",
                            fontSize = 13.sp,
                            color = Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    // 댓글 아이콘
                    Row (
                        verticalAlignment = Alignment.Bottom
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.comments_black),
                            contentDescription = "Comments Icon",
                            modifier = Modifier
                                .padding(2.dp)
                                .size(24.dp)
                        )
                        Text(
                            text = "$commentsCounter",
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Column (
                    modifier = Modifier.verticalScroll(commentsScrollState)
                ) {
                    CreateComment()
                    CreateComment()
                    CreateComment()
                    CreateComment()
                    CreateComment()
                }
            }
        }
    }
}


// 나중에 viewModel로 바꾸기
@Composable
fun CreateComment(content:String = "에어팟 봤는데"){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.gray_background))
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.comments_gray),
            contentDescription = "Comment Icon",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = content)
    }
}

@Composable
fun CreateIcon(imageVector: ImageVector = Icons.Filled.FavoriteBorder, onClick:()->Unit){
    Icon(
        imageVector = imageVector,
        contentDescription = "like",
        tint = Color.Red,
        modifier = Modifier
            .padding(2.dp)
            .size(24.dp)
            .clickable { onClick() }
    )
}