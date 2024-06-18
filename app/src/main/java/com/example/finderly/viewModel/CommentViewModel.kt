package com.example.finderly.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.Comment
import com.example.finderly.Data.CommentRequest
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CommentViewModel(private val application: Application) : AndroidViewModel(application) {

    private val _commentList = MutableLiveData<MutableList<Comment>>(mutableListOf())
    val commentList: LiveData<MutableList<Comment>> get() = _commentList

    private val _errorMessage = MutableLiveData<String?>()

    fun addComment(
        userId: String,
        postId: String,
        postCategory: Int,
        content: String,
        secretCheck: Boolean,
        postViewModel: PostViewModel
    ) {
        viewModelScope.launch {
            try {
                val commentRequest = CommentRequest(userId, postId, postCategory, content, secretCheck)
                Log.d("CommentViewModel", "Request: $commentRequest")


                val response = RetrofitInstance.api.postComment(commentRequest)
                if (response.message == "댓글 등록 완료") {
                    val newComment = Comment(response.commentId, userId, content, secretCheck)
                    val updatedList = _commentList.value.orEmpty().toMutableList()
                    updatedList.add(newComment)
                    _commentList.value = updatedList // LiveData 업데이트
                    postViewModel.addCommentToPost(newComment) // PostViewModel의 댓글 목록 업데이트


                    Log.d("CommentViewModel", "성공")
                } else {
                    Log.d("CommentViewModel", "Unexpected response or error adding comment: ${response.message}")
                }
            } catch (e: HttpException) {
                Log.e("CommentViewModel", "HTTP error: ${e.message}")
                _errorMessage.value = "HTTP error: ${e.message}"
            } catch (e: Exception) {
                Log.e("CommentViewModel", "Exception when adding comment: ${e.message}")
                _errorMessage.value = "Exception: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    fun deleteComment(commentId: String, commentViewModel: CommentViewModel, postViewModel: PostViewModel) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.deleteComment(commentId)
                if (response.message == "댓글 삭제 완료") {
                    val updatedList = _commentList.value.orEmpty().toMutableList()
                    val commentToRemove = updatedList.find { it.commentId == commentId }

                    if(commentToRemove != null) {
                        updatedList.remove(commentToRemove)
                        _commentList.value = updatedList
                        postViewModel.deleteCommentToPost(commentToRemove, true, commentId)
                    } else{
                        postViewModel.deleteCommentToPost(newComment = null, false, commentId)
                    }
                    Log.d("CommentViewModel", "삭제 성공")
                } else {
                    Log.d("CommentViewModel", "Unexpected response message: ${response.message}")
                }
            } catch (e: Exception) {
                Log.d("CommentViewModel", "Exception: ${e.toString()}")
                _errorMessage.value = "Exception: ${e.message}"
                e.printStackTrace()
            }
        }
    }
}

