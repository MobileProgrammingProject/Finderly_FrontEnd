package com.example.finderly.mapscreen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.finderly.MapScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreenPage(navHostController: NavHostController) {
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) // 위치정보권환 확인
    )

    val context = LocalContext.current
    var userLocation by remember { mutableStateOf(LatLng(37.5665, 126.9780)) }

    LaunchedEffect(locationPermissionState) {
        locationPermissionState.launchMultiplePermissionRequest()
    }

    if (locationPermissionState.allPermissionsGranted) {
        getUserLocation(context) { location ->
            userLocation = LatLng(location.latitude, location.longitude)
        }
        MapScreen(initialPosition = userLocation, navHostController)
    }
    else{
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("위치 권한을 허용해 주세요.")
        }
        MapScreen(initialPosition = LatLng(37.5665, 126.9780), navHostController)
        // 일단은 서울로 위치 띄워줌
        // 회의 통해서 정하기
    }
}

@SuppressLint("MissingPermission")
fun getUserLocation(context: Context, onLocationReceived: (Location) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            location?.let { onLocationReceived(it) }
        }
}

