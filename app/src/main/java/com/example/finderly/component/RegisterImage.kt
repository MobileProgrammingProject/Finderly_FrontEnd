package com.example.finderly.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finderly.R
import com.example.finderly.viewModel.ImageViewModel


@Composable
fun CreateImage(image: Int, containerSize:Dp, imageSize:Dp = containerSize, color: Color = Color.Gray){
    Box(modifier = Modifier
        .size(containerSize)
        .clip(RoundedCornerShape(20.dp))
        .border(
            BorderStroke(1.dp, color),
            shape = RoundedCornerShape(20.dp)
        ),
    ){
        Image(
            painter = painterResource(
                id = image,
            ),
            contentDescription = "picture",
            modifier = Modifier
                .size(imageSize)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun RegisterImage(imgViewModel: ImageViewModel = viewModel()){
    // 사진 등록
    // 뷰 모델 말고 다른 걸로 바꿔줘야 함
    val imgScrollState = rememberScrollState()

    Row (
        modifier = Modifier
            .horizontalScroll(imgScrollState)
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 이미지 추가
        Box(
            // Dialog 추가
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .clickable { }
        ) {
            CreateImage(image = R.drawable.plus_gray, 150.dp, 80.dp, color = Color.Gray)
        }
        imgViewModel.imageList.forEach {
            CreateImage(image = it.image, containerSize = 150.dp)
        }
    }

}

@Composable
fun ShowImage(imgViewModel: ImageViewModel = viewModel(), containerSize: Dp,imageSize:Dp = containerSize, color: Color = Color.Transparent){
    val imgScrollState = rememberScrollState()

    Row (
        modifier = Modifier
            .horizontalScroll(imgScrollState)
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        imgViewModel.imageList.forEach {
            CreateImage(image = it.image, containerSize = containerSize, imageSize, color)
        }

    }
}