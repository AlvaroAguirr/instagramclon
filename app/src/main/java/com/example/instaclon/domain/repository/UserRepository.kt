package com.example.instaclon.domain.repository

import com.example.instaclon.domain.model.User
import com.example.instaclon.util.Response
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    fun getsUSerDetails(userid: String): Flow<Response<User>>
    fun setUserDetails(
        userid:String,
        name:String,
        userName:String,
        bio:String,
        websiteUrl:String
    ):Flow<Response<Boolean>>
}