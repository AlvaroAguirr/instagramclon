package com.example.instaclon.data

import com.example.instaclon.domain.model.Post
import com.example.instaclon.domain.repository.PostRepository
import com.example.instaclon.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore

): PostRepository

{
    private var operationSuccessful=false
    override fun getAllPosts(userid: String):
            Flow<Response<List<Post>>> = callbackFlow {
                Response.Loading
        val snapshotListenner = firebaseFirestore.collection("posts")
            .whereNotEqualTo("userId",userid)
            .addSnapshotListener{snapshot,e->
                val response = if(snapshot!= null){
                    val postsList =snapshot.toObjects(Post::class.java)
                    Response.Success<List<Post>>(postsList)
                }
                else{
                    Response.Error(e?.message?:e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose{
            snapshotListenner.remove()
        }
            }

    override fun uploadPost(
        postImage: String,
        postDescription: String,
        time: Long,
        userId: String,
        userName: String,
        userImage: String
    ): Flow<Response<Boolean>> =flow{
    operationSuccessful=false
    try{
        val postId = firebaseFirestore.collection("posts").document().id
        val post = Post(
            postImage=postImage,
            postDescription = postDescription,
            postId=postId,
            time = time,
            userName= userName,
            userImage= userImage,
            userId =userId

        )
        firebaseFirestore.collection("posts").document(postId).set(post)
            .addOnSuccessListener {
                operationSuccessful= true
            }.await()
        if(operationSuccessful){
            emit(Response.Success(operationSuccessful))
        }
        else{
            emit(Response.Error("post Upload Unsuccessful"))
        }
    }
    catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:" An Unexpected error"))
    }
    }

}