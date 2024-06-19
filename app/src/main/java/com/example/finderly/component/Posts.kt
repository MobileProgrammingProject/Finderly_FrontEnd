package com.example.finderly.component

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.Data.PostListItem
import com.example.finderly.R
import com.example.finderly.viewModel.PostViewModel

@Composable
fun PostItem(post: PostListItem,postCategory:Int, navController: NavHostController) {
    var expended by rememberSaveable {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .height(70.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, colorResource(id = R.color.green), RoundedCornerShape(12.dp))
            .clickable { navController.navigate("LostPost/$postCategory/${post.postId}") }
            .padding(12.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = post.postTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Box(//이거 뺐으면 좋겠음
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Box(modifier = Modifier){
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "menu",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                expended = true
                            }
                            .align(Alignment.CenterEnd)
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
                                Text(
                                    text = "신고하기",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                )
                            }
                        }
                    }
                }
            }
        }
        Text(
            text = post.postContent,
            fontSize = 13.sp,
        )
    }
    Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
fun PostList(postViewModel: PostViewModel = viewModel(),lostCheck:Boolean,postCategory:Int, navController: NavHostController){
    val scrollState = rememberLazyListState()
    val posts = if(lostCheck){
        postViewModel.getLostPosts().reversed()
    } else {
        postViewModel.getFoundPosts().reversed()
    }
    LazyColumn (
        state = scrollState,
        //verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(posts){ post ->
            PostItem(post = post, postCategory = postCategory, navController = navController)
        }
    }
}