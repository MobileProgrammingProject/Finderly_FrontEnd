package com.example.finderly.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finderly.R
import com.example.finderly.screen.userScreen.FindMoreScreen
import com.example.finderly.screen.userScreen.LoginScreen
import com.example.finderly.screen.searchScreen.LostItemInfoScreen
import com.example.finderly.screen.userScreen.MyPageScreen
import com.example.finderly.screen.userScreen.PostMoreScreen
import com.example.finderly.screen.searchScreen.RegisterLostItemScreen
import com.example.finderly.screen.searchScreen.SearchScreen
import com.example.finderly.screen.userScreen.SignUpScreen
import com.example.finderly.screen.userScreen.SplashScreen
import com.example.finderly.screen.mapScreen.MapScreenPage
import com.example.finderly.screen.postScreen.MainBoardScreen
import com.example.finderly.screen.postScreen.PostScreen
import com.example.finderly.screen.postScreen.RegisterSreen

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
            SignUpScreen(navController)
        }

        // 메인 페이지
        composable("Search") {
            // 분실물 검색
            SearchScreen(navController)
        }
        composable("RegisterLost") {
            // 분실물 등록
            RegisterLostItemScreen(navController)
        }
        composable("LostItemInfo") {
            // 분실물 상세 정보
            LostItemInfoScreen()
        }

        // 지도 페이지
        composable("Location") {
            // 지도 페이지
            MapScreenPage(navHostController = navController)
        }
        
        // 게시판 페이지
        composable(route = "PostBoard"){
            MainBoardScreen(navHostController = navController)
        }
        composable(route = "LostPost") {
            // 분실물 게시글 상세 페이지
            PostScreen(navHostController = navController, R.string.lost_category )
        }
        composable(route = "FoundPost") {
            // 습득물 게시글 상세 페이지
            PostScreen(navHostController = navController, R.string.found_category )
        }
        composable("RegisterPost") {
            // 게시글 등록
            RegisterSreen(navHostController = navController)
        }
        
        // 마이 페이지
        composable(route = "MyPage") {
            MyPageScreen(navController)
        }
        composable(route = "FindMore") {
            FindMoreScreen(navController)
        }
        composable(route = "LostPostMore") {
            PostMoreScreen(navController,0)
        }
        composable(route = "FindPostMore") {
            PostMoreScreen(navController,1)
        }
        
    }
}