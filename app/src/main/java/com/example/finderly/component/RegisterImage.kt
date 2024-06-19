package com.example.finderly.component

import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finderly.R
import com.example.finderly.viewModel.ImageViewModel
import java.io.ByteArrayOutputStream


@Composable
fun CreateImage(
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
fun RegisterImage(imgViewModel: ImageViewModel = viewModel()) {
    // 사진 등록
    // 뷰 모epf 말고 다른 걸로 바꿔줘야 함
    val imgScrollState = rememberScrollState()

    Row(
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
fun ShowImage(
    imgViewModel: ImageViewModel = viewModel(),
    containerSize: Dp,
    imageSize: Dp = containerSize,
    color: Color = Color.Transparent
) {
    val imgScrollState = rememberScrollState()

    Row(
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

@Composable
fun CameraCaptureAndImagePicker(
    onImageSelected:(Uri?)->Unit
) {
    val context = LocalContext.current

    // 카메라로 사진 찍어서 가져오기
    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { takenPoto ->
            if (takenPoto != null) {
                val baos = ByteArrayOutputStream()
                takenPoto.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    baos
                )
                val b: ByteArray = baos.toByteArray()
                val encoded: String = Base64.encodeToString(b, Base64.DEFAULT)
                
            } else {
                Toast(context)
            }
        }

    // 갤러리에서 사진 가져오기
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri:Uri? ->

        }
    
    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Button(onClick = { 
            cameraLauncher.launch(null)
        }) {
            Text(text="Take Photo")
        }
        Button(onClick = {
            galleryLauncher.launch("image/*")
        }) {
            Text(text = "Pick Photo from Gallery")
        }
    }
}

@Composable
@Preview
fun screen(){
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    Column {
        CameraCaptureAndImagePicker{uri->
            selectedImageUri = uri
        }

        selectedImageUri?.let { uri ->

        }
    }
}
