package com.example.finderly.screen.mapScreen

import com.google.android.gms.maps.model.LatLng

data class Mark(
    val name: String,
    val position: String
)
// 서버로 부터 받아온 값들 저장할 마커 데이터


data class MarkerData(val position: LatLng, val name: String, val location: String)
// 위치값 반환받은 진짜 마커 데이터
