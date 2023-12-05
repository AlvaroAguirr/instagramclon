package com.example.instaclon.domain.use_cases

import com.example.instaclon.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignOut @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke()= repository.firebaseSignOut()

}