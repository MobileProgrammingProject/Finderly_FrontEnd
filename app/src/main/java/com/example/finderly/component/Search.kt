package com.example.finderly.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finderly.R

@Composable
fun Search(search:MutableState<String>, searchHasFocus:MutableState<Boolean>) {

    val focusRequester = FocusRequester()

    val label = "Search"


    Row(
        modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .width(250.dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search_gray),
            contentDescription = "search",
            modifier = Modifier.size(24.dp)
        )
        if (!searchHasFocus.value && search.value.isEmpty()) {
            Text(
                text = label,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    focusRequester.requestFocus()
                })

        }

        BasicTextField(
            value = search.value,
            onValueChange = { search.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    searchHasFocus.value = focusState.isFocused
                },
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            singleLine = true,
        )
    }

}