package com.example.finderly.searchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.Data.LostItem
import com.example.finderly.R
import com.example.finderly.component.Appbar
import com.example.finderly.component.RegisterButton
import com.example.finderly.component.Search

@Composable
fun LostItemCard(item: LostItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.green),
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .height(70.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 12.dp, start = 15.dp, bottom = 10.dp, end = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "습득지역 : ${item.locationFound} / 보관장소 : ${item.locationStored}",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun FilterMenu(modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field_border_gray),
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        IconButton(onClick = { expanded = true }, modifier = Modifier.width(80.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = " 필터",
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.text_gray)
                )
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "ArrowDown",
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Option 1") },
                onClick = { /* TODO */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Option 2") },
                onClick = { /* TODO */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Option 3") },
                onClick = { /* TODO */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = null
                    )
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.finderly_logo_transparent),
            contentDescription = null,
            modifier = Modifier
                .offset(x = 240.dp, y = 100.dp)
                .size(width = 174.dp, height = 207.dp),
            contentScale = ContentScale.Crop
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        //content
        Column(
            modifier = Modifier
                .padding(bottom = 15.dp)
        ) {
            Text(
                text = "Finderly",
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.green)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "무엇을 찾고 계신가요?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_deepgreen)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "찾고 싶은 물건을\n검색해보세요",
                fontSize = 15.sp,
                color = colorResource(id = R.color.text_gray)
            )
            Spacer(modifier = Modifier.height(20.dp))
            RegisterButton("분실물 등록하기", navController, "RegisterLost")
        }

        //body
        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
            ) {

                // 검색 창
                var search by rememberSaveable {
                    mutableStateOf("")
                }
                var searchHasFocus by rememberSaveable {
                    mutableStateOf(false)
                }
                Search(search = remember { mutableStateOf(search) }, searchHasFocus = remember {
                    mutableStateOf(searchHasFocus)
                })

                // 필터 메뉴
                FilterMenu(
                    Modifier
                        .offset(20.dp)
                        .width(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            val item = LostItem(1, "AirPods Pro", "에어팟 주웠는데", "자양파출소", "건대입구역", "분실")
            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(20) {
                    LostItemCard(item, { navController.navigate("LostItemInfo") })
                }
            }
        }
    }
    Appbar(selected = 1, navController = navController)
}