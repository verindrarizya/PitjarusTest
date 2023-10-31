package com.verindrarizya.pitjarustest.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.verindrarizya.pitjarustest.data.source.local.dao.StoreDao
import com.verindrarizya.pitjarustest.data.source.local.entity.StoreEntity

@Database(entities = [StoreEntity::class], version = 1)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun storeDao(): StoreDao

}