package com.example.instaclon.domain.use_cases.AuthenticationUseCases

import com.example.instaclon.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password:String, userName:String)
    =repository.firebaseSignUp(email, password,userName)
}