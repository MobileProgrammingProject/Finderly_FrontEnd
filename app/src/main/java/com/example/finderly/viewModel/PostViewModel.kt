package com.example.finderly.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.Comment
import com.example.finderly.Data.Post
import com.example.finderly.Data.PostListItem
import com.example.finderly.Data.PostRequest
import com.example.finderly.Data.registerResponse
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch


class PostViewModel(application:Application):AndroidViewModel(application = application){
    // 분실물 게시판 리스트
    private var lostPostList = mutableStateListOf<PostListItem>()
    // 습득물 게시판 리스트
    private var foundPostList = mutableStateListOf<PostListItem>()
    var success by mutableStateOf<Boolean?>(null)
    var message by mutableStateOf<String?>(null)

    // 게시글 상세 정보
    var post = mutableStateOf(
        Post(
            postId = "loading...",
            postTitle = "loading...",
            postContent = "loading...",
            pictures = emptyList(),
            comments = emptyList(),
            commentsCnt = 0,
            userId = ""
        )
    )

    private val _comments = MutableLiveData<List<Comment>>()
    val commentlist: LiveData<List<Comment>> = _comments

    init {
        setPostList(0)
        updateComments()
        success = null
        message = null
    }



    // 게시글 리스트
    fun setPostList(postCategory: Int){
        viewModelScope.launch {
            try {
                val response:List<PostListItem> = RetrofitInstance.api.getPostList(postCategory)
                if(response.isEmpty()){
                    // 응답 에러 로직 처리
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
                        // 예외 처리 추가
                    }
                }
            }catch (err:Exception){
                // 에러 로직 처리 추가
                Log.e("[API 연결] : ", "실패")
            }
        }
    }

    // 게시글 등록
    fun registerPost(postRequest:PostRequest, callback:(registerResponse)->Unit){
        viewModelScope.launch {
            var response = registerResponse(null, null)
            try {
                response = RetrofitInstance.api.registerPost(postRequest)
                // 성공 실패 로직 추가
                if(response.message == "게시물 등록 완료"){
                    callback(response)
                    success = true
                    message = response.message
                }else{
                    response.message = "게시물 등록 실패"
                    callback(response)
                    success = false
                    message = "게시물 등록 실패"
                }
            }catch (e:Exception){
                response.message = "Error"
                callback(response)
                success = false
                message = "서버 에러"
            }
        }
    }

    // 게시글 상세 정보 조회
    fun getPostDetailInfo(postCategory: Int, postId:String){
        viewModelScope.launch {
            try {
                val response:Post = RetrofitInstance.api.getPostDetailInfo(postCategory, postId)
                Log.d("post 응답", "$response")
                post.value = response
                updateComments()
                // 처리 에러 추가

            }catch (e:Exception){
                // 에러 처리 로직 추가
                Log.e("Post API Error", "Failed to fetch post details", e)
            }
        }
    }

    // 게시글 삭제
    fun deletePost(postId:String, postCategory: Int){
        viewModelScope.launch {
            try{
                val response = RetrofitInstance.api.deletePost(postCategory = postCategory,postId = postId)
                if(response.message=="게시물 삭제 성공"){
                    // 성공 로직 처리
                    success = true
                    message = response.message
                }else{
                    // 실패 로직 처리
                    success = false
                    message = "게시글 삭제 실패"
                }
            }catch (e:Exception){
                // 에러 로직 처리
                Log.e("Delete API Error", "Failed to delete", e)
                success = false
                message = "서버 에러"
            }
        }
    }

    // 게시글 검색
    fun searchPostByTitle(postCategory: Int, keyword:String){
        viewModelScope.launch {
            try {
                val response:List<PostListItem> = RetrofitInstance.api.searchPostByTitle(postCategory,keyword)
                if(response.isEmpty()){
                    // 응답 에러 로직 처리
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
                        // 예외 처리 추가
                    }
                }
            }catch (e:Exception){
                Log.e("Search API Error", "Filed to search", e)
            }
        }
    }

    fun getLostPosts(): SnapshotStateList<PostListItem> {
        return lostPostList
    }

    fun getFoundPosts(): SnapshotStateList<PostListItem> {
        return foundPostList
    }

    fun updateComments(){
        _comments.value = post.value.comments
    }

    // 새로운 댓글 추가
    fun addCommentToPost(newComment: Comment) {
        val updatedComments = post.value.comments.toMutableList()
        updatedComments.add(newComment)
        post.value = post.value.copy(comments = updatedComments, commentsCnt = updatedComments.size)
        _comments.value = updatedComments
    }

    fun deleteCommentToPost(newComment: Comment?, temp: Boolean, commentId: String){
        if(temp) {
            val updatedComments = post.value.comments.toMutableList()
            updatedComments.remove(newComment)
            post.value = post.value.copy(comments = updatedComments)
            _comments.value = updatedComments
        }
        else {
            val updatedComments = post.value.comments.toMutableList()
            val commentToRemove = updatedComments.find { it.commentId == commentId }
            if (commentToRemove != null) {
                updatedComments.remove(commentToRemove)
                post.value = post.value.copy(comments = updatedComments)
                _comments.value = updatedComments
            }
        }
    }
}

