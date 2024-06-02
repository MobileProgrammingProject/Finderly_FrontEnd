package com.example.finderly.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finderly.R
import com.example.finderly.postScreen.MainBoardScreen
import com.example.finderly.postScreen.PostScreen
import com.example.finderly.postScreen.RegisterSreen

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "PostBoard"){

        composable("Login") {
            // 로그인 페이지
        }
        // 메인 페이지
        composable("Search") { 
            // 검색 페이지
        }
        composable("Location") {
            // 지도 페이지
        }
        composable(route = "PostBoard"){
            MainBoardScreen(navHostController = navController)
        }
        composable("MyPage") {
            // 마이 페이지
        }
        
        // 서브 페이지
        composable(route = "LostPost") {
            PostScreen(navHostController = navController, R.string.lost_category )
        }
        composable(route = "FoundPost") {
            PostScreen(navHostController = navController, R.string.found_category )
        }
        composable("RegisterPost") { 
            RegisterSreen(navHostController = navController)
        }
    }
}