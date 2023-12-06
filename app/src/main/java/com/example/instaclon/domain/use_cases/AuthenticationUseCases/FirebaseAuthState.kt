package com.example.instaclon.domain.use_cases.AuthenticationUseCases

import com.example.instaclon.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(
    private val repository: AuthenticationRepository)
{
    operator fun invoke()= repository.getFirebaseAuthState()
}