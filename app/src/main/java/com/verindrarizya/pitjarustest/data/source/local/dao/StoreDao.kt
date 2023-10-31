package com.verindrarizya.pitjarustest.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.verindrarizya.pitjarustest.data.source.local.entity.StoreEntity

@Dao
interface StoreDao {

    @Query("SELECT * FROM store")
    fun getAllStore(): LiveData<StoreEntity>

    @Insert
    suspend fun insertAllStore(storeEntities: List<StoreEntity>)

}