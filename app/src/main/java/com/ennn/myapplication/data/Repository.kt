package com.ennn.myapplication.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.ennn.myapplication.storage.PrefKeys
import com.ennn.myapplication.storage.SettingsPreferences
import com.ennn.myapplication.storage.mapSettingsPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val dataStore: DataStore<Preferences>) {

    suspend fun getSettings(): Flow<SettingsPreferences> {
        val environmentStateFlow: Flow<SettingsPreferences> = dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading preferences.", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                mapSettingsPreferences(preferences)
            }
        return environmentStateFlow
    }

    suspend fun writeSettings(stateApplication: SettingsPreferences) {
        dataStore.edit { preferences ->
            preferences[PrefKeys.IP_ADDRESS] = stateApplication.ipAddress
            preferences[PrefKeys.PORT_ADDRESS] = stateApplication.portAddress
            preferences[PrefKeys.NAME_DATABASE] = stateApplication.nameDatabase
            preferences[PrefKeys.LOGIN_DATABASE] = stateApplication.loginDatabase
            preferences[PrefKeys.PASSWORD_DATABASE] = stateApplication.passwordDatabase
            preferences[PrefKeys.SERVER_KEY_DATABASE] = stateApplication.serverKeyDatabase
        }
    }
}