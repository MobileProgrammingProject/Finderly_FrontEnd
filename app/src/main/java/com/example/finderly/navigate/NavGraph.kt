package com.example.finderly.Navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finderly.R
import com.example.finderly.screen.FindMoreScreen
import com.example.finderly.screen.LoginScreen
import com.example.finderly.screen.LostItemInfoScreen
import com.example.finderly.screen.MyPageScreen
import com.example.finderly.screen.PostMoreScreen
import com.example.finderly.screen.RegisterLostItemScreen
import com.example.finderly.screen.RegisterScreen
import com.example.finderly.screen.SearchScreen
import com.example.finderly.screen.SplashScreen
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
            // 분실물 검색
            SearchScreen(navController)
        }
        composable("RegisterLost") {
            // 분실물 등록
            RegisterLostItemScreen(navController)
        }
        composable("LostItemInfo") {
            // 분실물 상세 정보
            LostItemInfoScreen(navController)
        }

        // 지도 페이지
        composable("Location") {
            // 지도 페이지
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
        composable(route = "PostMore") {
            PostMoreScreen(navController)
        }
        
    }
}