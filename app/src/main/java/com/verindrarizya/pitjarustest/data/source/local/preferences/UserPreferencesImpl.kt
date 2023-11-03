package com.verindrarizya.pitjarustest.data.source.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferences {

    private val usernamePreferencesKey = stringPreferencesKey("USER_NAME_PREFERENCES_KEY")

    override val username: Flow<String?> = dataStore.data.map { preferences ->
        preferences[usernamePreferencesKey]
    }

    override suspend fun setUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[usernamePreferencesKey] = username
        }
    }

    override suspend fun clearData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}