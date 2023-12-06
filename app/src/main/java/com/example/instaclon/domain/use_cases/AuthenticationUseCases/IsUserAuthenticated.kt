package com.example.instaclon.domain.use_cases.AuthenticationUseCases

import com.example.instaclon.domain.repository.AuthenticationRepository
import javax.inject.Inject

class IsUserAuthenticated  @Inject constructor(
    private val repository: AuthenticationRepository
){
    operator fun invoke()= repository.isUserAuthenticatedInFirebase()
}