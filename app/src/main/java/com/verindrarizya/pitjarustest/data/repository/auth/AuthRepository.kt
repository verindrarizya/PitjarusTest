package com.verindrarizya.pitjarustest.data.repository.auth

import com.verindrarizya.pitjarustest.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(username: String, password: String): Flow<Resource<String>>

}