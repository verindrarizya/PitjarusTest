package com.verindrarizya.pitjarustest.data.repository.auth

import com.verindrarizya.pitjarustest.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val username: Flow<String?>

    suspend fun login(username: String, password: String): Flow<Resource<String>>

    suspend fun logout()

}