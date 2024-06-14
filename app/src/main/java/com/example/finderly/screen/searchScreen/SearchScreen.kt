package com.example.finderly.screen.searchScreen

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.Data.LostItem
import com.example.finderly.R
import com.example.finderly.component.Appbar
import com.example.finderly.component.RegisterButton
import com.example.finderly.component.Search
import com.example.finderly.viewModel.LostViewModel

@Composable
fun LostItemCard(item: LostItem, onClick: () -> Unit, deleteClick: ()-> Unit) {
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
        Row(
            modifier = Modifier
                .padding(top = 10.dp, start = 15.dp, bottom = 10.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.lostName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = "습득지역 : ${item.lostLocation} / 보관장소 : ${item.storage}",
                    fontSize = 14.sp
                )
            }
            DeleteOrReportMenu(modifier = Modifier.offset(x = 0.dp, y = (-20).dp), deleteClick, {})
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
//            DropdownMenuItem(
//                text = { Text("Option 1") },
//                onClick = { /* TODO */ },
//                leadingIcon = {
//                    Icon(
//                        Icons.Outlined.Edit,
//                        contentDescription = null
//                    )
//                })
//            DropdownMenuItem(
//                text = { Text("Option 2") },
//                onClick = { /* TODO */ },
//                leadingIcon = {
//                    Icon(
//                        Icons.Outlined.Settings,
//                        contentDescription = null
//                    )
//                })
//            DropdownMenuItem(
//                text = { Text("Option 3") },
//                onClick = { /* TODO */ },
//                leadingIcon = {
//                    Icon(
//                        Icons.Outlined.Email,
//                        contentDescription = null
//                    )
//                })
        }
    }
}

@Composable
fun DeleteOrReportMenu(modifier : Modifier, deleteClick: () -> Unit, reportClick : ()-> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .width(20.dp)
            .height(20.dp)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.green),
                shape = CircleShape
            )
            .background(color = Color.White, shape = CircleShape)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "MoreVert",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(
                onClick = { deleteClick() },
                modifier = Modifier
                    .size(90.dp, 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "삭제하기",
                        fontSize = 12.sp,
                        color = Color.Gray,
                    )
                }
            }
            DropdownMenuItem(
                onClick = { reportClick() },
                modifier = Modifier
                    .size(90.dp, 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "신고하기",
                        fontSize = 12.sp,
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreen(navController: NavHostController) {

    val lostViewModel : LostViewModel = viewModel()
    LaunchedEffect(Unit) {
        lostViewModel.lostList()
    }
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
            .padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 85.dp)
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
                    mutableStateOf(searchHasFocus)},  onSearchClicked = {
                        lostViewModel.lostSearch(it)
                }
                )

                // 필터 메뉴
                FilterMenu(
                    Modifier
                        .offset(20.dp)
                        .width(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                itemsIndexed(lostViewModel.lostItemList){ _, item ->
                    LostItemCard(item,{navController.navigate("LostItemInfo/${item.lostId}")}, {
                        lostViewModel.lostDelete(item.lostId)
                        lostViewModel.lostList()
                    }
                    )
                }
            }
        }
    }
    Appbar(selected = 1, navController = navController)
}