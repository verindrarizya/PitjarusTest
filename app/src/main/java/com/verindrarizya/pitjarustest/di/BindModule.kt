package com.verindrarizya.pitjarustest.di

import com.verindrarizya.pitjarustest.data.repository.auth.AuthRepository
import com.verindrarizya.pitjarustest.data.repository.auth.AuthRepositoryImpl
import com.verindrarizya.pitjarustest.data.repository.store.StoreRepository
import com.verindrarizya.pitjarustest.data.repository.store.StoreRepositoryImpl
import com.verindrarizya.pitjarustest.data.source.local.preferences.UserPreferences
import com.verindrarizya.pitjarustest.data.source.local.preferences.UserPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindStoreRepository(storeRepositoryImpl: StoreRepositoryImpl): StoreRepository

    @Binds
    abstract fun bindUserPreferences(userPreferencesImpl: UserPreferencesImpl): UserPreferences

}