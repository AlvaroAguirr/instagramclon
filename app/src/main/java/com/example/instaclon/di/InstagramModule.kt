package com.example.instaclon.di

import com.example.instaclon.data.AuthenticationRepositoryImpl
import com.example.instaclon.data.PostRepositoryImpl
import com.example.instaclon.data.UserRepositoryImpl
import com.example.instaclon.domain.repository.AuthenticationRepository
import com.example.instaclon.domain.repository.PostRepository
import com.example.instaclon.domain.repository.UserRepository
import com.example.instaclon.domain.use_cases.AuthenticationUseCases.AuthenticationUseCases
import com.example.instaclon.domain.use_cases.AuthenticationUseCases.FirebaseAuthState
import com.example.instaclon.domain.use_cases.AuthenticationUseCases.FirebaseSignIn
import com.example.instaclon.domain.use_cases.AuthenticationUseCases.FirebaseSignOut
import com.example.instaclon.domain.use_cases.AuthenticationUseCases.FirebaseSignUp
import com.example.instaclon.domain.use_cases.AuthenticationUseCases.IsUserAuthenticated
import com.example.instaclon.domain.use_cases.PostUseCases.GetAllPosts
import com.example.instaclon.domain.use_cases.PostUseCases.PostUseCases
import com.example.instaclon.domain.use_cases.PostUseCases.UploadPost
import com.example.instaclon.domain.use_cases.UserUseCases.GetUserDetails
import com.example.instaclon.domain.use_cases.UserUseCases.SetUserDetails
import com.example.instaclon.domain.use_cases.UserUseCases.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.UserDataReader
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InstagramModule {
    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()

    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth = auth, firestore = firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository)
    )

    @Singleton
    @Provides
    fun provideUserRepository(firebaseFirestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }
    @Singleton
    @Provides
    fun provideUserUseCases(repository: UserRepository)= UserUseCases(
        getUserDetails=GetUserDetails(repository=repository),
        setUserDetails= SetUserDetails(repository=repository)
    )

    @Provides
    @Singleton
    fun providePostRepository(firebaseFirestore: FirebaseFirestore):PostRepository{
        return PostRepositoryImpl(firebaseFirestore=firebaseFirestore)
    }
    fun providePostUseCases(repository: PostRepository) = PostUseCases(
        getAllPosts = GetAllPosts(repository= repository),
        uploadPost = UploadPost(repository = repository)
    )


}











