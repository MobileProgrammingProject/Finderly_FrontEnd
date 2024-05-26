package com.example.finderly

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finderly.ui.theme.Purple80

@Composable
fun LostItemCard(item: LostItem, onClick: () -> Unit) {
    Card(modifier = Modifier
        .size(width = 350.dp, height = 100.dp)
        .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Purple80),
        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
        Column(modifier = Modifier.padding(top = 20.dp, start = 10.dp, bottom = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center) {
            Text(text = item.name, fontSize = 20.sp)
            Text(text = "습득지역 : ${item.locationFound} / 보관장소 : ${item.locationStored}")
        }
    }
}