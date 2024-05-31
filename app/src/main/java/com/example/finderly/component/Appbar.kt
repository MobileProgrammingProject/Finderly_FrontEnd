package com.example.finderly.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.R


@Composable
fun Appbar(
    selected:Int,
    navController: NavHostController
) {
    val imageSize:Dp = 20.dp
    val fontSize = 10.sp

    var searchImage = R.drawable.ic_search_gray
    var searchColor = R.color.field_text_gray
    var locationImage = R.drawable.ic_location_gray
    var locationColor = R.color.field_text_gray
    var listImage = R.drawable.ic_list_gray
    var listColor = R.color.field_text_gray
    var myPageImage = R.drawable.ic_mypage_gray
    var myPageColor = R.color.field_text_gray

    when (selected){
        R.string.search-> {
            searchImage = R.drawable.ic_search_green
            searchColor = R.color.green
        }
        R.string.location -> {
            locationImage = R.drawable.ic_location_green
            locationColor = R.color.green
        }
        R.string.post -> {
            listImage = R.drawable.ic_list_green
            listColor = R.color.green
        }
        R.string.myPage -> {
            myPageImage = R.drawable.ic_mypage_green
            myPageColor = R.color.green
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ){
        Column {
            Divider(
                color = colorResource(id = R.color.field_border_gray)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                CreateTap(navController, "Search", searchImage, searchColor, imageSize, fontSize)
                CreateTap(navController, "Location", locationImage, locationColor, imageSize, fontSize)
                CreateTap(navController, "MainBoard", listImage, listColor, imageSize, fontSize)
                CreateTap(navController, "MyPage", myPageImage, myPageColor, imageSize, fontSize)
            }
        }
    }
}

@Composable
fun CreateTap(navController: NavHostController, route:String, image:Int, text:Int,imageSize:Dp, fontSize:TextUnit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(end = 10.dp)
            .clickable {
                navController.navigate(route)
            }
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.size(imageSize)
        )
        Text(
            text = "MY",
            fontSize = fontSize,
            color = colorResource(id = text)
        )
    }
}