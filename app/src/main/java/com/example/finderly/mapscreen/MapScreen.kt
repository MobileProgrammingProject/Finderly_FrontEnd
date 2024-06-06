package com.example.finderly

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finderly.component.Appbar
import com.example.finderly.mapscreen.Mark
import com.example.finderly.mapscreen.MarkerData
import com.example.finderly.mapscreen.loadMarkers
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(initialPosition: LatLng, navHostController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var zoom by remember { mutableStateOf(15f) }
    var targetName by remember { mutableStateOf("") } // search를 통해 받아올 이름
    // var targetCategory =  // search를 통해 받아올 카테고리
    var markers by remember { mutableStateOf(listOf<MarkerData>()) }

    val markList = listOf(
        Mark(name = "에어팟", position = "서울강남경찰서", category = "전자기기"),
        Mark(name = "에어팟", position = "서울서초경찰서", category = "전자기기"),
        Mark(name = "충전기", position = "건국대학교", category = "전자기기"),
        Mark(name = "아이폰", position = "서울용산경찰서", category = "전자기기")
    ) // 예시, 구현 중 확인용, 실제에선 값이 바뀔 수 있으므로 mutable 쓸 것

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, zoom) // 강남구 위치
    } // 현재 위치를 초기값으로 바꾸기
    // 가장 마지막 zoom 계산하여 넣어줄 함수 추가 구현

    LaunchedEffect(Unit) {
        loadMarkers(context, markList, coroutineScope) { newMarkers ->
            markers = newMarkers // 위 작업을 통해 markers에 조건에 해당하는 marker값들 들어감
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                GoogleMap(   // 지도 관련 composables입니다.
                    cameraPositionState = cameraPositionState
                ) {
                    // 상태를 이용하여 마커를 표시
                    markers.forEach { markerData ->
                        if (markerData.name.contains(targetName)) // 검색한 값 포함하는 것만 보여주기
                            Marker(
                                // onClick = {},
                                state = MarkerState(position = markerData.position), // 마커 클릭 시, 마커를 중심으로 카메라 이동
                                title = markerData.name,  // 마커 클릭 시, name값 넘겨줘서 보여줌 (물건 이름)
                                snippet = markerData.location // 마커 클릭 시, category값 넘겨줘서 보여줌 (물건 카테고리)
                                // snippet = markerData.category 마커 클릭 시, category값 넘겨줘서 보여줌 (물건 카테고리)
                                // 원하는 정보 추가로 여기에 추가해서 보여줄 수 있음
                            )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                ) {
                    Row(modifier = Modifier
                        .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        TextField(
                            label = { Text("Search") },
                            value = targetName,
                            onValueChange = { targetName = it },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_search_green),
                                    contentDescription = "search"
                                )
                            },
                            shape = MaterialTheme.shapes.small.copy(all = CornerSize(16.dp)),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            )
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.header1)),                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text(text = "Filter", color = Color.White, style = TextStyle(fontSize = 12.sp))
                        }
                    }
                }
            }
            Appbar(selected = 2, navController = navHostController)
        }
    }
}
