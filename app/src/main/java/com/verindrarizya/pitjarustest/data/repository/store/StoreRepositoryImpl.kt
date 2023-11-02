package com.verindrarizya.pitjarustest.data.repository.store

import android.util.Log
import com.verindrarizya.pitjarustest.data.source.local.dao.StoreDao
import com.verindrarizya.pitjarustest.presentation.model.Store
import com.verindrarizya.pitjarustest.util.DataMapper
import com.verindrarizya.pitjarustest.util.Resource
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

    override fun getStore(id: Int): Flow<Store> {
        return storeDao.getStore(id).map { DataMapper.storeEntityToUiModel(it) }
    }

    override suspend fun updateStore(store: Store): Resource<String> {
        val storeEntity = DataMapper.storeUiModelToEntity(store)

        val successfulUpdatedRows = storeDao.updateStore(storeEntity)
        Log.d("StoreTag", "updatedRows = $successfulUpdatedRows ${storeEntity.id}")

        return if (successfulUpdatedRows > 0) {
            Log.d("StoreTag", "updateStore: success")
            Resource.Success("Store Updated")
        } else {
            Log.d("StoreTag", "updateStore: failed")
            Resource.Failure("Failed, please try again!")
        }
    }

}