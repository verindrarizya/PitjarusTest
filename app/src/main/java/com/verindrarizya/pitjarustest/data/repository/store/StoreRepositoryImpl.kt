package com.verindrarizya.pitjarustest.data.repository.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.verindrarizya.pitjarustest.data.source.local.dao.StoreDao
import com.verindrarizya.pitjarustest.presentation.model.Store
import com.verindrarizya.pitjarustest.util.DataMapper
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDao: StoreDao
) : StoreRepository {
    override fun getAllStore(): LiveData<List<Store>> {
        return storeDao.getAllStore().map { storeEntities ->
            storeEntities.map { DataMapper.storeEntityToUiModel(it) }
        }
    }

}