package com.example.finderly.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finderly.R

@Composable
@Preview
fun PostHeader(
    subTitle:Int = R.string.lost_category,
    backgroundColor:Color = Color.Transparent,
    title:Int = R.color.white,
    subTitl:Int = R.color.white
){
    val padding:Dp = 30.dp
    val height:Dp = 170.dp
    val titleColor:Int = title
    val subTitleColor:Int = subTitl
    val bottom:Dp = 650.dp

    Column (
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
            .background(color = backgroundColor)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = titleColor),
            modifier = Modifier.padding(top = padding, start = padding)
        )
        Text(
            text = stringResource(id = subTitle),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = subTitleColor),
            modifier = Modifier.padding(start = padding)
        )
    }
}