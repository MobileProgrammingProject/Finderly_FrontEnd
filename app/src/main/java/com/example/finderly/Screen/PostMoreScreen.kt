package com.example.finderly.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.Data.MyPost
import com.example.finderly.R
import com.example.finderly.component.Appbar
import com.example.finderly.component.getUserId
import com.example.finderly.viewModel.UserViewModel

@Composable
fun PostList(
    Scrollstate : ScrollState,
    items : List<MyPost>,
    navController: NavHostController,
    postCategory : Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(Scrollstate)
    ) {
        items.forEach{
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.green),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(15.dp)
                    .clickable {
                        // 해당 게시물 상세 페이지로 이동
                        if(postCategory == 0){
                            navController.navigate("LostPost/$postCategory/${it.postId}")
                        }
                        else if(postCategory == 1){
                            navController.navigate("FoundPost/$postCategory/${it.postId}")
                        }
                    }
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "${it.title}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_more_green),
                        contentDescription = "더보기",
                        modifier = Modifier
                            .size(13.dp)
                    )
                }

                Text(
                    text = "${it.detail}",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.field_text_gray)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun PostMoreScreen(navController: NavHostController, postType: Int) { // 0이면 분실물, 1이면 습득물
    val scrollstate = rememberScrollState()
    var LostCheck by remember {
        mutableStateOf(postType == 0)
    }
    var FindCheck by remember {
        mutableStateOf(postType == 1)
    }
    val userViewModel : UserViewModel = viewModel()
    val context = LocalContext.current
    var userId = getUserId(context).toString() // userId 저장

    LaunchedEffect(Unit) {
        userViewModel.userProfile(userId)
    }
    val profile = userViewModel.profile

    val MyLostPost = profile?.lostPosts?.map {
        MyPost(it.postTitle, it.postContent, it.postId)
    }

    val MyFindPost = profile?.foundPosts?.map {
        MyPost(it.postTitle, it.postContent, it.postId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .padding(start = 30.dp, top = 30.dp, bottom = 15.dp)
        ){
            Text(
                text = "Finderly",
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.green)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "내 게시글",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_deepgreen)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "내가 쓴 글을\n한번에 모아보세요",
                fontSize = 15.sp,
                color = colorResource(id = R.color.text_gray)
            )
        }

        Box(
            modifier = Modifier.height(50.dp)
        ) {
            Divider(
                color = colorResource(id = R.color.green),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(1.dp)
            )

            Row(
                modifier = Modifier.padding(start = 25.dp)
            ) {
                Column(
                    modifier = Modifier
                        .size(90.dp, 50.dp)
                        .clickable {
                            LostCheck = true
                            FindCheck = false
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "분실물",
                        fontSize = 16.sp,
                        color = if (LostCheck) colorResource(id = R.color.text_deepgreen) else colorResource(id = R.color.text_gray)
                    )

                    if (LostCheck) {
                        Divider(
                            color = Color.Black,
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
                            LostCheck = false
                            FindCheck = true
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "습득물",
                        fontSize = 16.sp,
                        color = if (FindCheck) colorResource(id = R.color.text_deepgreen) else colorResource(id = R.color.text_gray)
                    )

                    if (FindCheck) {
                        Divider(
                            color = Color.Black,
                            modifier = Modifier
                                .width(90.dp)
                                .height(2.dp)
                                .offset(y = 13.dp)
                        )
                    }
                }
            }
        }

        if(LostCheck){
            if (MyLostPost != null) {
                PostList(Scrollstate = scrollstate, items = MyLostPost, navController,0)
            }
        }
        else if(FindCheck){
            if (MyFindPost != null) {
                PostList(Scrollstate = scrollstate, items = MyFindPost, navController,1)
            }
        }

    }
    Appbar(selected = 4, navController = navController)
}