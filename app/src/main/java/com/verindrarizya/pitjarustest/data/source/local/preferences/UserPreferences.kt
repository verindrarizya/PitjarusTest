package com.verindrarizya.pitjarustest.data.source.local.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferences {

    val username: Flow<String?>

    suspend fun setUsername(username: String)

    suspend fun clearData()

}