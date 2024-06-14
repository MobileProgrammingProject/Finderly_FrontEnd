package com.example.finderly.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.finderly.Data.Comment
import com.example.finderly.Data.CommentRequest
import com.example.finderly.R
import com.example.finderly.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CommentViewModel(private val application: Application) : AndroidViewModel(application) {

    private val _commentList = MutableLiveData<MutableList<Comment>>(mutableListOf())
    val commentList: LiveData<MutableList<Comment>> get() = _commentList

    private val _loading = MutableLiveData<Boolean>()

    private val _errorMessage = MutableLiveData<String?>()

    fun addComment(
        userId: String,
        postId: String,
        postCategory: Int,
        content: String,
        secretCheck: Boolean
    ) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val commentRequest = CommentRequest(userId, postId, postCategory, content, secretCheck)
                Log.d("CommentViewModel", "Request: $commentRequest")

                val response = RetrofitInstance.api.postComment(commentRequest)
                if (response.message == "댓글 등록 완료") {
                    val newComment = Comment(response.commentId, userId, content, secretCheck)
                    val updatedList = _commentList.value.orEmpty().toMutableList()
                    updatedList.add(newComment)
                    _commentList.value = updatedList // LiveData 업데이트
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
            } finally {
                _loading.value = false
            }
        }
    }

    fun deleteComment(commentId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = RetrofitInstance.api.deleteComment(commentId)
                if (response.message == "댓글 삭제 완료") {
                    _commentList.value = _commentList.value?.filter { it.commentId != commentId }?.toMutableList()
                    Log.d("CommentViewModel", "삭제 성공")
                } else {
                    Log.d("CommentViewModel", "Unexpected response message: ${response.message}")
                }
            } catch (e: Exception) {
                Log.d("CommentViewModel", "Exception: ${e.toString()}")
                _errorMessage.value = "Exception: ${e.message}"
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment, commentViewModel: CommentViewModel) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.gray_background))
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.comments_gray),
            contentDescription = "Comment Icon",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = comment.content)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "menu",
                    modifier = Modifier
                        .clickable { expanded = true }
                        .size(20.dp)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .align(Alignment.Center)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "신고하기",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        onClick = { expanded = false },
                        modifier = Modifier.size(90.dp, 20.dp)
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "삭제하기",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        onClick = {
                            commentViewModel.deleteComment(comment.commentId)
                            expanded = false},
                        modifier = Modifier.size(90.dp, 20.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

