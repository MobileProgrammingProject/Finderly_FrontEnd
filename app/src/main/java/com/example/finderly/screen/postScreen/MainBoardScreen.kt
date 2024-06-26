package com.example.finderly.screen.postScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.component.Appbar
import com.example.finderly.component.PostHeader
import com.example.finderly.component.PostList
import com.example.finderly.component.RegisterButton
import com.example.finderly.component.Search
import com.example.finderly.component.TapMenu
import com.example.finderly.component.getUserId
import com.example.finderly.viewModel.CommentViewModel
import com.example.finderly.viewModel.PostViewModel

@Composable
fun MainBoardScreen(
    navHostController: NavHostController
) {

    val focusManager = LocalFocusManager.current
    val postViewModel : PostViewModel = viewModel()

    var lostCheck by rememberSaveable {
        mutableStateOf(true)
    }
    var findCheck by rememberSaveable {
        mutableStateOf(false)
    }
    var search by rememberSaveable {
        mutableStateOf("")
    }
    val searchHasFocus by rememberSaveable {
        mutableStateOf(false)
    }

    val postCategory:Int = if(lostCheck){
        0
    }else{
        1
    }

    val context = LocalContext.current
    val userId = getUserId(context).toString()

    LaunchedEffect(Unit){
        postViewModel.setPostList(postCategory)
    }
    LaunchedEffect(postCategory) {
        postViewModel.setPostList(postCategory)
    }
    LaunchedEffect(postViewModel.success) {
        if(postViewModel.success == true){
            Toast.makeText(context, postViewModel.message, Toast.LENGTH_SHORT).show()
        }
        else if(postViewModel.success == false){
            Toast.makeText(context, postViewModel.message, Toast.LENGTH_SHORT).show()
        }
    }

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
            // 게시글 등록 버튼
            Box(modifier = Modifier.padding(top = 130.dp, start = 30.dp)) {
                RegisterButton("게시글 등록하기", navHostController, "RegisterPost")
            }
        }

        // 탭 메뉴
        TapMenu(lostCheck, findCheck) { lostCheck = !lostCheck; findCheck = !findCheck }


        // 게시글 리스트
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.lightgreen))
                .padding(start = 30.dp, end = 30.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 검색 필드
            Spacer(modifier = Modifier.padding(13.dp))
            Search(search = remember {
                mutableStateOf(search)
            }, searchHasFocus = remember {
                mutableStateOf(searchHasFocus)
            }, width = 280.dp, onSearchClicked = {
                if(it != ""){
                    postViewModel.searchPostByTitle(postCategory, it)
                }
                else{
                    postViewModel.setPostList(postCategory)
                }
            })
            Spacer(modifier = Modifier.padding(13.dp))
            
            // 리스트
            PostList( lostCheck = lostCheck, postCategory = postCategory, navController = navHostController)

        }
    }
    Appbar(selected = 3, navController = navHostController)
}