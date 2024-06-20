package com.example.finderly.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.finderly.R


@Composable
fun CreateImageByResource(
    image: Int,
    containerSize: Dp,
    imageSize: Dp = containerSize,
    color: Color = Color.Gray
) {
    Box(
        modifier = Modifier
            .size(containerSize)
            .clip(RoundedCornerShape(20.dp))
            .border(
                BorderStroke(1.dp, color),
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "picture",
            modifier = Modifier
                .size(imageSize)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )

    }
}


@Composable
fun CreateImage(
    image: Uri?,
    containerSize: Dp,
    imageSize: Dp = containerSize,
    color: Color = Color.Gray
) {
    Box(
        modifier = Modifier
            .size(containerSize)
            .clip(RoundedCornerShape(20.dp))
            .border(
                BorderStroke(1.dp, color),
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        if (image == null) {
            Image(
                painter = painterResource(id = R.drawable.plus_gray),
                contentDescription = "Register Picture",
                modifier = Modifier
                    .size(imageSize)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = "picture",
                modifier = Modifier
                    .size(imageSize)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ShowImage(
    imgList: List<String>,
    containerSize: Dp,
    imageSize: Dp = containerSize,
    color: Color = Color.Transparent
) {
    val imgScrollState = rememberScrollState()

    Row (
        modifier = Modifier
            .horizontalScroll(imgScrollState)
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CreateImageByResource(R.drawable.lostitemexampleimage, containerSize = containerSize, imageSize, color)
        CreateImageByResource(R.drawable.lostitemexampleimage, containerSize = containerSize, imageSize, color)
        CreateImageByResource(R.drawable.lostitemexampleimage, containerSize = containerSize, imageSize, color)
        CreateImageByResource(R.drawable.lostitemexampleimage, containerSize = containerSize, imageSize, color)
        CreateImageByResource(R.drawable.lostitemexampleimage, containerSize = containerSize, imageSize, color)
    }
}

@Composable
fun CameraCaptureAndImagePicker(
    onImageSelected: (Uri?) -> Unit
) {
    val context = LocalContext.current

    // 갤러리에서 사진 가져오기
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            onImageSelected(uri)
        }

    Box(
        // Dialog 추가
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                galleryLauncher.launch("image/*")
            }
    ) {
        CreateImage(image = null, 150.dp, 80.dp, color = Color.Gray)
    }

}
