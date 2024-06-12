package com.example.finderly.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.PostListItem
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch


class PostViewModel(private val application:Application):AndroidViewModel(application = application){
    // 분실물 게시판 리스트
    private var lostPostList = mutableStateListOf<PostListItem>()

    // 습득물 게시판 리스트
    private var foundPostList = mutableStateListOf<PostListItem>()

    init {
        setPostList(0)
    }

    fun setPostList(postCategory: Int){
        viewModelScope.launch {
            try {
                val response:List<PostListItem> = RetrofitInstance.api.getPostList(postCategory)
                if(response.isEmpty()){

                }else{
                    if(postCategory==0){
                        // 분실물 게시판 처리
                        lostPostList.clear()
                        lostPostList.addAll(response)
                    }
                    else if (postCategory==1){
                        // 습득물 게시판 처리
                        foundPostList.clear()
                        foundPostList.addAll(response)
                    }
                    else{
                        // 예외 처리
                    }
                }
            }catch (err:Exception){
                // 에러 로직 처리 추가
                Log.e("[API 연결] : ", "실패")
            }
        }
    }

    fun getLostPosts(): SnapshotStateList<PostListItem> {
        return lostPostList
    }

    fun getFoundPosts(): SnapshotStateList<PostListItem> {
        return foundPostList
    }

}
