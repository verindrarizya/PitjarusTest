package com.verindrarizya.pitjarustest.data.repository.store

import com.verindrarizya.pitjarustest.presentation.model.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    fun getAllStore(): Flow<List<Store>>

}