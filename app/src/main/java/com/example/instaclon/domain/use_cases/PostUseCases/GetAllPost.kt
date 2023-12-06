package com.example.instaclon.domain.use_cases.PostUseCases

import com.example.instaclon.domain.repository.PostRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: PostRepository

) {
    operator fun invoke(userid:String)=repository.getAllPosts(userid=userid)
}