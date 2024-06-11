package com.example.finderly.postScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.PostHeader
import com.example.finderly.component.ShowImage
import com.example.finderly.viewModel.UserViewModel


@Composable
fun PostScreen(navHostController: NavHostController, postType:Int, postCategory: Int, postId: String){
    val userViewModel : UserViewModel = viewModel()
    Log.d("postCategory","$postCategory")
    Log.d("postId","$postId")

    LaunchedEffect(Unit) {
        userViewModel.postitemInfo(postCategory,postId)
    }
    val postitemInfo = userViewModel.postiteminfo
    Log.d("postitemInfo","$postitemInfo")

    Box{
        PostHeader(postType)    // 헤더 컴포넌트
        
        // 좋아요 수
        var likeCounter by rememberSaveable {
            mutableStateOf(0)
        }
        // 댓글 수
        var commentsCounter by rememberSaveable {
            mutableStateOf(postitemInfo?.comments?.size)
        }
        // 댓글 입력(등록)
        var commentContent by rememberSaveable {
            mutableStateOf("댓글을 등록하세요.")
        }

        val imgScrollState = rememberScrollState()
        //val commentsScrollState = rememberScrollState()
        val topPadding = 150.dp
        val contentScrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .verticalScroll(contentScrollState)
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
                        .clickable { expended = true }
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
                    text = "${postitemInfo?.postTitle}",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // 내용
                Text(
                    text = "${postitemInfo?.postContent}",
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.padding(20.dp))

                // 사진
                ShowImage(containerSize = 180.dp, imageSize = 180.dp, color = Color.Transparent)

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
                    //modifier = Modifier.verticalScroll(commentsScrollState)
                ) {
                    CreateComment()
                    CreateComment()
                    CreateComment()
                    CreateComment()
                    CreateComment()
                }
                Spacer(modifier = Modifier.height(10.dp))
                // 댓글 등록 창
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(width = 1.dp, color = colorResource(id = R.color.field_border_gray))
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row{
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = { /* 익명 체크 로직 */ }
                            )
                            Text(
                                text = "익명",
                                fontSize = 10.sp
                            )
                            
                            Spacer(modifier = Modifier.width(10.dp))

                            BasicTextField(
                                value = commentContent,
                                onValueChange = {}
                            )


                        }
                    }

                    Button(
                        onClick = { /* 등록 버튼 클릭 로직 */ },
                        modifier = Modifier
                            .padding(8.dp)
                            .width(75.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("등록",
                            color = Color.White)
                    }
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

// 좋아요 업데이트
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