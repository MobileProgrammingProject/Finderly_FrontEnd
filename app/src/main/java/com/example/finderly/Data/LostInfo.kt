package com.example.finderly.Data

//분실물 등록 응답
data class LostItemResponse(
    val message: String,
    val lostId: String
)


data class LostItemListElement(
    val lostId: String,
    val lostName: String,
    val lostLocation: String,
    val storage: String
)


//분실물 등록
data class LostItemRequest(
    val userId: String,
    val lostName: String,
    val lostLocation: String,
    val lostDate: String,
    val storage: String,
    val description: String,
    val pictures: List<String>
)