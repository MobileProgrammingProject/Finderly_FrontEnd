package com.example.finderly.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.finderly.R
import com.example.finderly.viewModel.Post
import com.example.finderly.viewModel.PostViewModel

@Composable
fun PostItem(post:Post, navController: NavHostController) {
    Column (
        modifier = Modifier
            .clickable {navController.navigate("LostPost")}
            .background(Color.White)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, colorResource(id = R.color.text_deepgreen), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = post.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Image(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "menu",
                alignment = Alignment.CenterEnd,
                modifier = Modifier.fillMaxWidth().size(20.dp)
            )
        }
        Text(
            text = post.content,
            fontSize = 13.sp,
        )
    }
}

@Composable
fun PostList(postViewModel: PostViewModel = viewModel(), navController: NavHostController){
    val scrollState = rememberLazyListState()
    LazyColumn (
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(postViewModel.postList){ _, item ->
            PostItem(post = item, navController)
        }
    }
}