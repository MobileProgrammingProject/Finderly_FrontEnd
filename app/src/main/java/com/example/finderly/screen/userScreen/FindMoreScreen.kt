package com.example.finderly.screen.userScreen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.Data.MyFindItem
import com.example.finderly.R
import com.example.finderly.component.Appbar
import com.example.finderly.component.getUserId
import com.example.finderly.viewModel.UserViewModel

@Composable
fun FindMoreScreen(navController: NavHostController) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
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
                .padding(15.dp)
                .verticalScroll(scrollstate)
        ) {
            MyFindItems?.forEach{
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
                            navController.navigate("LostItemInfo/${it.lostId}")
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
    Appbar(selected = 4, navController = navController)
}