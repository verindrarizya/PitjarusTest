package com.verindrarizya.pitjarustest.di

import android.content.Context
import androidx.room.Room
import com.verindrarizya.pitjarustest.data.source.local.StoreDatabase
import com.verindrarizya.pitjarustest.data.source.local.dao.StoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideStoreDatabase(
        @ApplicationContext context: Context
    ): StoreDatabase {
        return Room.databaseBuilder(
            context,
            StoreDatabase::class.java,
            "pitjarus_store_database"
        ).build()
    }

    @Singleton
    @Provides
    fun providesStoreDao(storeDatabase: StoreDatabase): StoreDao {
        return storeDatabase.storeDao()
    }
}