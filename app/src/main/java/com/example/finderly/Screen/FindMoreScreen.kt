package com.example.finderly.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.Data.MyFindItem
import com.example.finderly.R

@Composable
fun FindMoreScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()

    // 습득물 예시 데이터 리스트
    val MyFindItems = listOf(
        MyFindItem("AirPods Pro 2", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 3", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 4", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 5", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 2", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 3", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 4", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 5", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 2", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 3", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 4", "건대입구역","자양파출소"),
        MyFindItem("AirPods Pro 5", "건대입구역","자양파출소")
    )

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
        Text(
            text = "내 습득물",
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
                .verticalScroll(scrollstate)
        ) {
            MyFindItems.forEach{
                Column(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.green),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(15.dp)
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
                                .clickable {
                                    // 해당 게시물 상세 페이지로 이동
                                }
                        )
                    }

                    Text(
                        text = "습득지역 : ${it.location} / 보관장소 : ${it.storagePlace}",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.field_text_gray)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
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