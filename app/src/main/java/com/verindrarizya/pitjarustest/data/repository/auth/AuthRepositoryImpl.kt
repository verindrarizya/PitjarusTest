package com.verindrarizya.pitjarustest.data.repository.auth

import com.verindrarizya.pitjarustest.data.source.local.dao.StoreDao
import com.verindrarizya.pitjarustest.data.source.local.preferences.UserPreferences
import com.verindrarizya.pitjarustest.data.source.remote.api.ApiService
import com.verindrarizya.pitjarustest.util.DataMapper
import com.verindrarizya.pitjarustest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val storeDao: StoreDao,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override val username: Flow<String?> = userPreferences.username

    override suspend fun login(username: String, password: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading)

        try {
            val loginResponse = apiService.login(username, password)
            userPreferences.setUsername(username)

            val storeResponse = loginResponse.stores

            val storeEntities = storeResponse.map { DataMapper.storeResponseToEntity(it) }
            storeDao.insertAllStore(storeEntities)

            emit(Resource.Success("Login Success!"))
        } catch (e: Exception) {
            emit(Resource.Failure(message = e.message ?: "Something went wrong"))
        }
    }

    override suspend fun logout() {
        userPreferences.clearData()
    }
}