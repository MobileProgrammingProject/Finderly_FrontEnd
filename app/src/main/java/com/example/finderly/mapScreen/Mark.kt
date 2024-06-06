package com.example.finderly.mapScreen

import com.google.android.gms.maps.model.LatLng

data class Mark(
    val name: String,
    val position: String,
    val category: String
)
// 서버로 부터 받아온 마커 데이터
// 서버에서 Mark 데이터만 만들수 있게 넘겨주시면 됩니다.


data class MarkerData(val position: LatLng, val name: String, val category: String, val location: String) {
}
// 위치값 반환받은 진짜 마커 데이터
