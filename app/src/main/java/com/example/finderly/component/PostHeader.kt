package com.example.finderly.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finderly.R

@Composable
fun PostHeader(
    subTitle:Int = R.string.lost_category,
    backgroundColor:Color = Color.Transparent,
    title:Int = R.color.white,
    subTitl:Int = R.color.white
){
    val padding:Dp = 30.dp
    val height:Dp = 180.dp
    val titleColor:Int = title
    val subTitleColor:Int = subTitl

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(id = R.color.header1),
                        colorResource(id = R.color.header2)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, with(LocalDensity.current) { height.toPx() })
                )
            )
            .background(color = backgroundColor),

    ) {
        Column (
            modifier = Modifier
                .padding(top = padding, start = padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = titleColor),

            )
            Text(
                text = stringResource(id = subTitle),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = subTitleColor),
                modifier = Modifier
            )
        }
    }
}