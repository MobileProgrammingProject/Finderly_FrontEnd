package com.example.finderly.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finderly.R
import com.example.finderly.Screen.FindMoreScreen
import com.example.finderly.Screen.LoginScreen
import com.example.finderly.Screen.MyPageScreen
import com.example.finderly.Screen.PostMoreScreen
import com.example.finderly.Screen.RegisterScreen
import com.example.finderly.Screen.SplashScreen
import com.example.finderly.postScreen.MainBoardScreen
import com.example.finderly.postScreen.PostScreen
import com.example.finderly.postScreen.RegisterSreen

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "Splash"){
        // 로그인, 회원가입 페이지
        composable(route = "Splash") {
            SplashScreen(navController)
        }
        composable(route = "Login"){
            LoginScreen(navController)
        }
        composable(route = "Register") {
            RegisterScreen(navController)
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
        composable(route = "MyPage") {
            MyPageScreen(navController)
        }
        composable(route = "FindMore") {
            FindMoreScreen(navController)
        }
        composable(route = "PostMore") {
            PostMoreScreen(navController)
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