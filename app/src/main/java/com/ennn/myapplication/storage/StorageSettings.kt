package com.ennn.myapplication.storage

import androidx.datastore.preferences.core.Preferences

fun mapSettingsPreferences(preferences: Preferences): SettingsPreferences {
    val ipAddress = preferences[PrefKeys.IP_ADDRESS]?.toString() ?: ""
    val portAddress = preferences[PrefKeys.PORT_ADDRESS]?.toString() ?: ""
    val nameDatabase = preferences[PrefKeys.NAME_DATABASE]?.toString() ?: ""
    val loginDatabase = preferences[PrefKeys.LOGIN_DATABASE]?.toString() ?: ""
    val passwordDatabase = preferences[PrefKeys.PASSWORD_DATABASE]?.toString() ?: ""
    val serverKeyDatabase = preferences[PrefKeys.SERVER_KEY_DATABASE]?.toString() ?: ""

    return SettingsPreferences(
        ipAddress,
        portAddress,
        nameDatabase,
        loginDatabase,
        passwordDatabase,
        serverKeyDatabase
    )
}

data class SettingsPreferences(
    val ipAddress: String,
    val portAddress: String,
    val nameDatabase: String,
    val loginDatabase: String,
    val passwordDatabase: String,
    val serverKeyDatabase: String
)