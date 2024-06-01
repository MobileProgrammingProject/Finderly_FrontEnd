package com.example.finderly.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finderly.Screen.FindMoreScreen
import com.example.finderly.Screen.LoginScreen
import com.example.finderly.Screen.MyPageScreen
import com.example.finderly.Screen.PostMoreScreen
import com.example.finderly.Screen.RegisterScreen
import com.example.finderly.Screen.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "PostMore"){
        composable(route = "Splash") {
            SplashScreen(navController)
        }
        composable(route = "Login"){
            LoginScreen(navController)
        }
        composable(route = "Register") {
            RegisterScreen(navController)
        }
        composable(route = "MyPage") {
            MyPageScreen(navController)
        }
        composable(route = "FindMore") {
            FindMoreScreen(navController)
        }
        composable(route = "PostMore") {
            PostMoreScreen(navController)
        }
        composable(route = "Search") {
            // 검색페이지
        }
        composable(route = "Location") {
            // 지도페이지
        }
        composable(route = "List") {
            // 게시판페이지
        }
    }
}