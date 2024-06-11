package com.example.finderly.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.Data.MyFindItem
import com.example.finderly.Data.MyPost
import com.example.finderly.R
import com.example.finderly.component.Appbar
import com.example.finderly.component.getUserId
import com.example.finderly.viewModel.UserViewModel

@Composable
fun MyPageList(
    items: List<MyPost>,
    TopTitle: Int,
    navController: NavHostController,
    postType : Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(id = TopTitle),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.lightgreen),
                    shape = RoundedCornerShape(20.dp)
                )
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column{
                    items.take(2).forEach{
                        Column(
                            modifier = Modifier
                                .clickable {
                                    // 해당 게시물 상세 페이지로 이동
                                    if(postType == 0){
                                        navController.navigate("LostPost/$postType/${it.postId}")
                                    }
                                    else if(postType == 1){
                                        navController.navigate("FoundPost/$postType/${it.postId}")
                                    }
                                }
                        ){
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp, top = 12.dp),
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
                                color = colorResource(id = R.color.field_text_gray),
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Divider(
                        color = colorResource(id = R.color.field_border_gray),
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                // 분실물, 습득물 게시글 페이지로 이동
                                if (postType == 0) {
                                    navController.navigate("LostPostMore")
                                } else {
                                    navController.navigate("FindPostMore")
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "더보기",
                            fontSize = 11.sp,
                            color = colorResource(id = R.color.field_text_gray),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_more_black),
                            contentDescription = "더보기 버튼",
                            modifier = Modifier.size(13.dp)
                        )

                    }
                }
            }
        }
    }
}
@Composable
fun MyPageScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()
    val userViewModel : UserViewModel = viewModel()
    val context = LocalContext.current
    var userId = getUserId(context).toString() // userId 저장

    LaunchedEffect(Unit) {
        userViewModel.userProfile(userId)
    }
    val profile = userViewModel.profile

    val MyFindItems = profile?.founds?.map {
        MyFindItem(it.lostName, it.lostLocation, it.storage, it.lostId)
    }

    val MyLostPost = profile?.lostPosts?.map {
        MyPost(it.postTitle, it.postContent, it.postId)
    }

    val MyFindPost = profile?.foundPosts?.map {
        MyPost(it.postTitle, it.postContent, it.postId)
    }

    Column (
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(scrollstate)
            .padding(30.dp)
    ) {
        Text(
            text = "Finderly",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = R.color.green)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    Text(
                        text = "${userViewModel.profile?.userName}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.text_deepgreen)
                    )
                    Text(
                        text = " 님",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text(
                        text = "신고 :",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " ${userViewModel.profile?.reports}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "회",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "로그아웃",
                    fontSize = 13.sp,
                    color = colorResource(id = R.color.field_text_gray),
                    modifier = Modifier.clickable {
                        userViewModel.initializeState()
                        Toast.makeText(context, "${userViewModel.profile?.userName} 님 로그아웃 성공", Toast.LENGTH_SHORT).show()
//                        userViewModel.logout(userId)
                        navController.navigate("Splash")
                    }
                )

//                LaunchedEffect(userViewModel.success) {
//                    if(userViewModel.success == true){
//                        Toast.makeText(context, userViewModel.message, Toast.LENGTH_SHORT).show()
//                        navController.navigate("Splash")
//                    }
//                    else if(userViewModel.success == false){
//                        Toast.makeText(context, userViewModel.message, Toast.LENGTH_SHORT).show()
//                        Log.d("Logout", "Logout Failed")
//                    }
//                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(10.dp)
        ) {
            Text(
                text = "내 습득물",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(id = R.color.lightgreen),
                        shape = RoundedCornerShape(20.dp)
                    )
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        MyFindItems?.take(2)?.forEach{
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp, top = 12.dp)
                                    .clickable {
                                        // 해당 게시물 상세 페이지로 이동
                                        navController.navigate("LostItemInfo/${it.lostId}")
                                    },
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
                                text = "습득지역 : ${it.location} / 보관장소 : ${it.storagePlace}",
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.field_text_gray),
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Divider(
                            color = colorResource(id = R.color.field_border_gray),
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    //습득물 페이지로 이동
                                    navController.navigate("FindMore")
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "더보기",
                                fontSize = 11.sp,
                                color = colorResource(id = R.color.field_text_gray)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_more_black),
                                contentDescription = "더보기 버튼",
                                modifier = Modifier.size(13.dp)
                            )

                        }
                    }
                }
            }
        }

        if (MyLostPost != null) {
            MyPageList(items = MyLostPost, R.string.my_lost_post, navController,0)
        }
        if (MyFindPost != null) {
            MyPageList(items = MyFindPost, R.string.my_found_post, navController,1)
        }
        Spacer(modifier = Modifier.height(80.dp))
    }

    Appbar(selected = 4, navController = navController)
}