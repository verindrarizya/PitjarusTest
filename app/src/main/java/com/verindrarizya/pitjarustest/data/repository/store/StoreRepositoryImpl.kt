package com.verindrarizya.pitjarustest.data.repository.store

import com.verindrarizya.pitjarustest.data.source.local.dao.StoreDao
import com.verindrarizya.pitjarustest.presentation.model.Store
import com.verindrarizya.pitjarustest.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDao: StoreDao
) : StoreRepository {
    override fun getAllStore(): Flow<List<Store>> {
        return storeDao.getAllStore().map { storeEntities ->
            storeEntities.map { DataMapper.storeEntityToUiModel(it) }
        }
    }

}