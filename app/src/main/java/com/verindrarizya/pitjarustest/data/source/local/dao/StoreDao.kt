package com.verindrarizya.pitjarustest.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.verindrarizya.pitjarustest.data.source.local.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {

    @Query("SELECT * FROM store")
    fun getAllStore(): Flow<List<StoreEntity>>

    @Query("SELECT * FROM STORE WHERE id = :id")
    fun getStore(id: Int): Flow<StoreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStore(storeEntities: List<StoreEntity>)

    @Update
    suspend fun updateStore(storeEntity: StoreEntity): Int

}