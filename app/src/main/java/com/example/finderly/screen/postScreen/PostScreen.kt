package com.example.finderly.screen.postScreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finderly.Data.Comment
import com.example.finderly.R
import com.example.finderly.component.PostHeader
import com.example.finderly.component.ShowImage
import com.example.finderly.component.getUserId
import com.example.finderly.viewModel.CommentViewModel
import com.example.finderly.viewModel.PostViewModel


@Composable
fun PostScreen(
    postType: Int,
    postCategory: Int,
    postId: String,
    navHostController: NavHostController
) {
    //val userViewModel: UserViewModel = viewModel()
    val postViewModel: PostViewModel = viewModel()
    val context = LocalContext.current
    val commentViewModel: CommentViewModel = viewModel()
    val userId = getUserId(context)

    Log.d("postCategory", "$postCategory")
    Log.d("postId", postId)

    LaunchedEffect(Unit) {
        //userViewModel.postitemInfo(postCategory, postId)
        postViewModel.getPostDetailInfo(postCategory, postId)
    }

    //val postitemInfo = userViewModel.postiteminfo
    var post = postViewModel.post
    val comments by postViewModel.commentlist.observeAsState()
    Log.d("Post", "${post.value.comments.size}")

    // 좋아요 수
    var likeCounter by rememberSaveable {
        mutableStateOf(0)
    }
    // 댓글 수
    var commentsCounter by rememberSaveable {
        mutableIntStateOf(post.value.comments.size)
    }
    // 댓글 입력(등록)
    var commentContent by rememberSaveable {
        mutableStateOf("")
    }

    var secretCheck by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(postViewModel.post) {
        post = postViewModel.post
        commentsCounter = post.value.comments.size
    }

    Box {
        PostHeader(postType)    // 헤더 컴포넌트

        val topPadding = 140.dp
        val contentScrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .verticalScroll(contentScrollState)
                .padding(top = topPadding)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(30.dp)
        ) {

            var expended by rememberSaveable {
                mutableStateOf(false)
            }

            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.TopEnd)
            ) {
                Image(painter = painterResource(
                    id = R.drawable.menu
                ),
                    contentDescription = "menu",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expended = true }
                )
                DropdownMenu(
                    expanded = expended,
                    onDismissRequest = { expended = false },
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
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
                        onClick = { expended = false },
                        modifier = Modifier
                            .size(90.dp, 20.dp)
                            .align(Alignment.CenterHorizontally)
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
                            expended = false
                            postViewModel.deletePost(postId = postId, postCategory = postCategory)
                            navHostController.navigate("PostBoard"){
                                popUpTo("PostBoard"){inclusive = true}
                            }
                        },
                        modifier = Modifier
                            .size(90.dp, 20.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            Column(
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                // 타이틀
                Text(
                    text = post.value.postTitle,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // 내용
                Text(
                    text = post.value.postContent,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.padding(20.dp))

                // 사진
                ShowImage(containerSize = 180.dp, imageSize = 180.dp, color = Color.Transparent)

                // 좋아요 & 댓글 아이콘
                Row(
                    modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                ) {
                    // 좋아요 아이콘
                    Row(
                        //modifier = Modifier.align(Alignment.Bottom)
                        verticalAlignment = Alignment.Bottom
                    ) {
                        CreateIcon(Icons.Filled.FavoriteBorder) { likeCounter++ }
                        Text(
                            text = "$likeCounter",
                            fontSize = 13.sp,
                            color = Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    // 댓글 아이콘
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.comments_black),
                            contentDescription = "Comments Icon",
                            modifier = Modifier
                                .padding(2.dp)
                                .size(24.dp)
                        )
                        Text(
                            text = "${post.value.comments.size}",
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Log.d("PostScreen", "Post Info loaded: ${post.value}")
                Column {
                    comments.orEmpty().forEach { comment ->
                        CommentItem(comment, commentViewModel, postViewModel, context)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

            }
        }
        // 댓글 등록 창
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 30.dp, end = 30.dp, bottom = 20.dp)
            ) {

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.field_border_gray),
                            RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Row {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(50.dp)
                        ) {
                            Checkbox(
                                checked = secretCheck,
                                onCheckedChange = { secretCheck=it }
                            )
                            Text(
                                text = "익명",
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            BasicTextField(
                                value = commentContent,
                                onValueChange = {commentContent = it},
                                modifier = Modifier
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done
                                )
                            )

                        }
                    }

                    Button(
                        onClick = {
                            commentViewModel.addComment(userId.toString(), postId, postCategory, commentContent, secretCheck, postViewModel)
                            commentContent = "" // 댓글 등록 후 입력 필드 초기화
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .width(75.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "등록",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CommentItem(comment: Comment, commentViewModel: CommentViewModel, postViewModel: PostViewModel,context: Context) {
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
                            if(comment.userId == getUserId(context)){
                            commentViewModel.deleteComment(comment.commentId, commentViewModel, postViewModel)}
                            expanded = false },
                        modifier = Modifier.size(90.dp, 20.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}


// 좋아요 업데이트
@Composable
fun CreateIcon(imageVector: ImageVector = Icons.Filled.FavoriteBorder, onClick: () -> Unit) {
    Icon(
        imageVector = imageVector,
        contentDescription = "like",
        tint = Color.Red,
        modifier = Modifier
            .padding(2.dp)
            .size(24.dp)
            .clickable { onClick() }
    )
}