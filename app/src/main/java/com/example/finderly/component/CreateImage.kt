package com.example.finderly.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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


@Composable
fun CreateImage(image:Int, containerSize:Dp, imageSize:Dp = containerSize, color: Color = Color.Gray){
    Box(modifier = Modifier
        .size(containerSize)
        .clip(RoundedCornerShape(20.dp))
        .border(BorderStroke(1.dp, color),
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
                //.clip(RoundedCornerShape(30.dp))
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )
    }
}