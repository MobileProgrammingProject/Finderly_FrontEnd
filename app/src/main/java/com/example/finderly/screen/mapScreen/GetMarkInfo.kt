@file:Suppress("DEPRECATION")

package com.example.finderly.screen.mapScreen

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// 아래 함수로, 구글 지도에서 Mark에있는 String 타입의 position 값->위도,LatLng로 반환
suspend fun getLatLngFromAddress(context: Context, address: String): LatLng? {
    return withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context)
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses?.isNotEmpty() == true) {
                val location = addresses[0]
                LatLng(location.latitude, location.longitude)
            } else {
                /*
                만약에 구글지도에서 위치이름으로 주소 찾지 못하면 이 부분 실행됨
                현재는 그냥 화면에 띄우지 않는데, 의논 필요
                */
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

suspend fun loadMarkers(
    context: Context,
    markList: List<Mark>,
    coroutineScope: CoroutineScope,
    onMarkersLoaded: (List<MarkerData>) -> Unit
) {
    coroutineScope.launch {
        val newMarkers = markList.mapNotNull { mark ->
            val latLng = getLatLngFromAddress(context, mark.position)
            if (latLng != null) {
                MarkerData(name = mark.name, position = latLng, category = mark.category, location = mark.position)
            } else {
                null
            }
        }
        onMarkersLoaded(newMarkers)
    }
}


