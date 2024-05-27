package com.example.finderly.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MyPageScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollstate)
    ) {
    }
}