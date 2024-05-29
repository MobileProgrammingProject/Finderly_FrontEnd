package com.example.finderly.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.Data.MyPost
import com.example.finderly.R

@Composable
fun PostList(
    Title : String,
    Scrollstate : ScrollState,
    items : List<MyPost>
) {
    Text(
        text = Title,
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
    Spacer(modifier = Modifier.height(5.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.lightgreen),
                    shape = RoundedCornerShape(20.dp)
                )
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .verticalScroll(Scrollstate)
            ) {
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
                        items.forEach{
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
                    }
                }
                Spacer(modifier = Modifier.height(35.dp))
            }
        }
    }
}
@Composable
fun PostMoreScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.finderly_logo_transparent),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopEnd)
                .padding(start = 40.dp)
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Text(
            text = "Finderly",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = R.color.green)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // 타입에 따라 분실물, 습득물 게시글 띄우기
        PostList("내 분실물 게시글",scrollstate,MyLostPost)
        //PostList(Title = "내 습득물 게시글", Scrollstate = scrollstate, items = MyFindPost)
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