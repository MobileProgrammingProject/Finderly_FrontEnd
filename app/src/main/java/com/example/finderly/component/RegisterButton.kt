package com.example.finderly.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.finderly.R


@Composable
fun RegisterButton(text:String, navHostController: NavHostController, screen:String){
    Button(
        onClick = {navHostController.navigate(screen)},
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.lightgreen)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.plus_deepgreen),
            contentDescription = "plus",
            modifier = Modifier
                .size(20.dp)
                .padding(end = 10.dp)
        )
        Text(
            text = text,
            color = colorResource(id = R.color.text_deepgreen),
            fontWeight = FontWeight.ExtraBold
        )
    }
}