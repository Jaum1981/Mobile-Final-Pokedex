package com.example.pokedexappv2.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

object DataStoreUtils {
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    private val NOTIFICATIONS_KEY = booleanPreferencesKey("notifications_enabled")

    fun isDarkModeEnabled(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[DARK_MODE_KEY] ?: false // Valor padrão: false
        }
    }

    suspend fun setDarkModeEnabled(context: Context, isEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isEnabled
        }
    }

    fun areNotificationsEnabled(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[NOTIFICATIONS_KEY] ?: true // Valor padrão: true
        }
    }

    suspend fun setNotificationsEnabled(context: Context, isEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_KEY] = isEnabled
        }
    }
}
