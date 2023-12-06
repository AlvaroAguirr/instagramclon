package com.example.instaclon.domain.use_cases.UserUseCases

import com.example.instaclon.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails  @Inject constructor(
    private val repository: UserRepository

){
    operator fun invoke (
        userid:String,
        name: String,
        userName: String,
        bio:String,
        websiteUrl:String
    ) =
        repository.setUserDetails(
            userid=userid,
            name=name,
            userName=userName,
            bio=bio,
            websiteUrl =websiteUrl
            )
}