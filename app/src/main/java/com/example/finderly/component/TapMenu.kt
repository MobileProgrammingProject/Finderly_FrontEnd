package com.example.finderly.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finderly.R
import org.w3c.dom.Text

@Composable
fun TapMenu(){
    // 메뉴 탭
    var lostCheck by rememberSaveable {
        mutableStateOf(true)
    }
    var findCheck by rememberSaveable {
        mutableStateOf(false)
    }
    val textSize:TextUnit = 16.sp
    val underline = 3.dp

    Column {
        Row (
            modifier = Modifier.padding(start = 50.dp)
        ) {
            Column(
                modifier = Modifier
                    .size(90.dp, 50.dp)
                    .clickable {
                        lostCheck = true
                        findCheck = false
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "분실물",
                    fontSize = textSize,
                    color = if (lostCheck) colorResource(id = R.color.text_deepgreen) else colorResource(id = R.color.text_gray)
                )

                if (lostCheck) {
                    Divider(
                        color = colorResource(id = R.color.text_deepgreen),
                        modifier = Modifier
                            .width(90.dp)
                            .height(underline)
                            .offset(y = 13.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .size(90.dp, 50.dp)
                    .clickable {
                        lostCheck = false
                        findCheck = true
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "습득물",
                    fontSize = textSize,
                    color = if (findCheck) colorResource(id = R.color.text_deepgreen) else colorResource(
                        id = R.color.text_gray
                    )
                )

                if (findCheck) {
                    Divider(
                        color = colorResource(id = R.color.text_deepgreen),
                        modifier = Modifier
                            .width(90.dp)
                            .height(underline)
                            .offset(y = 13.dp)
                    )
                }
            }

        }
        Divider()
    }
}