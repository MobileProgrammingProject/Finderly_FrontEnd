package com.example.finderly.Screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.Data.MyFindItem
import com.example.finderly.Data.MyPost
import com.example.finderly.R

@Composable
fun Appbar(
    searchImage : Int,
    searchColor: Int,
    locationImage : Int,
    locationColor: Int,
    listImage : Int,
    listColor : Int,
    myPageImage : Int,
    myPageColor : Int,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ){
        Column {
            Divider(
                color = colorResource(id = R.color.field_border_gray)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 50.dp)
                        .clickable {
                            navController.navigate("Search")
                        }
                ) {
                    Image(
                        painter = painterResource(id = searchImage),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "검색",
                        fontSize = 15.sp,
                        color = colorResource(id = searchColor)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        navController.navigate("Location")
                    }
                ) {
                    Image(
                        painter = painterResource(id = locationImage),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "위치",
                        fontSize = 15.sp,
                        color = colorResource(id = locationColor)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        navController.navigate("List")
                    }
                ) {
                    Image(
                        painter = painterResource(id = listImage),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "게시판",
                        fontSize = 15.sp,
                        color = colorResource(id = listColor)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .clickable {
                            navController.navigate("MyPage")
                        }
                ) {
                    Image(
                        painter = painterResource(id = myPageImage),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "MY",
                        fontSize = 15.sp,
                        color = colorResource(id = myPageColor)
                    )
                }
            }
        }
    }
}

@Composable
fun MyPageList(
    items: List<MyPost>,
    TopTitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(15.dp)
    ) {
        Text(
            text = TopTitle,
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
                modifier = Modifier.fillMaxSize()
            ) {
                items.take(2).forEach{
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
                                .clickable {
                                    // 해당 게시물 상세 페이지로 이동
                                }
                        )
                    }

                    Text(
                        text = "${it.detail}",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.field_text_gray),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    color = colorResource(id = R.color.field_border_gray),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            // 분실물, 습득물 게시글 페이지로 이동
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
@Composable
fun MyPageScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()

    // 습득물 예시 데이터 리스트
    val MyFindItems = listOf(
        MyFindItem("AirPods Pro 2", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 3", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 4", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 5", "건대입구역","자양파출소")
    )

    // 분실물 게시글 예시 데이터 리스트
    val MyLostPost = listOf(
        MyPost("에어팟","에어팟 잃어버렸어요"),
        MyPost("아이폰","아이폰 잃어버렸어요")
    )

    // 습득물 게시글 예시 데이터 리스트
    val MyFindPost = listOf(
        MyPost("에어팟","에어팟 찾았어요"),
        MyPost("아이폰","아이폰 찾았어요")
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollstate)
            .padding(25.dp)
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
                .padding(15.dp),
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
                        text = "파인더리",
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
                        text = " 0",
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
                    modifier = Modifier.clickable { navController.navigate("Splash") }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .padding(15.dp)
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
                    modifier = Modifier.fillMaxSize()
                ) {
                    MyFindItems.take(2).forEach{
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
                                    .clickable {
                                        // 해당 게시물 상세 페이지로 이동
                                    }
                            )
                        }

                        Text(
                            text = "습득지역 : ${it.location} / 보관장소 : ${it.storagePlace}",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.field_text_gray),
                            modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(
                        color = colorResource(id = R.color.field_border_gray),
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                       //습득물 페이지로 이동
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

        MyPageList(items = MyLostPost, TopTitle = "내 분실물 게시글")
        MyPageList(items = MyFindPost, TopTitle = "내 습득물 게시글")
        Spacer(modifier = Modifier.height(80.dp))
    }

    Appbar(
        searchImage = R.drawable.ic_search_gray,
        searchColor = R.color.field_text_gray,
        locationImage = R.drawable.ic_location_gray,
        locationColor = R.color.field_text_gray,
        listImage = R.drawable.ic_list_gray,
        listColor = R.color.field_text_gray,
        myPageImage = R.drawable.ic_mypage_green,
        myPageColor = R.color.green,
        navController = navController
    )
}