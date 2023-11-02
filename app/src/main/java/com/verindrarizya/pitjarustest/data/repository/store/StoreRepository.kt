package com.verindrarizya.pitjarustest.data.repository.store

import com.verindrarizya.pitjarustest.presentation.model.Store
import com.verindrarizya.pitjarustest.util.Resource
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    fun getAllStore(): Flow<List<Store>>

    fun getStore(id: Int): Flow<Store>

    suspend fun updateStore(store: Store): Resource<String>

}