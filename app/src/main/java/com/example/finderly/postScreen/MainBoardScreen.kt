package com.example.finderly.postScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.Appbar
import com.example.finderly.component.FoundPostList
import com.example.finderly.component.LostPostList
import com.example.finderly.component.PostHeader
import com.example.finderly.component.Search
import com.example.finderly.component.TapMenu
import com.example.finderly.viewModel.LostPostViewModel

@Composable
fun MainBoardScreen(
    navHostController: NavHostController
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(Color.White)
            .clickable {
                focusManager.clearFocus()
            }
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
                onClick = {navHostController.navigate("RegisterPost")},
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

        // 탭 메뉴
        var lostCheck by rememberSaveable {
            mutableStateOf(true)
        }
        var findCheck by rememberSaveable {
            mutableStateOf(false)
        }
        TapMenu(lostCheck, findCheck, {lostCheck = !lostCheck; findCheck = !findCheck})


        // 게시글 리스트
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.lightgreen))
                .padding(start = 50.dp, end = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var search by rememberSaveable {
                mutableStateOf("")
            }
            var searchHasFocus by rememberSaveable {
                mutableStateOf(false)
            }

            // 검색 필드
            Spacer(modifier = Modifier.padding(13.dp))
            Search(search = remember {
                mutableStateOf(search)
            }, searchHasFocus = remember {
                mutableStateOf(searchHasFocus)
            } )
            Spacer(modifier = Modifier.padding(13.dp))
            
            // 리스트
            if(lostCheck){
                LostPostList(navController = navHostController)
            }else{
                FoundPostList(navController = navHostController)
            }
        }
    }
    Appbar(selected = 3, navController = navHostController)
}