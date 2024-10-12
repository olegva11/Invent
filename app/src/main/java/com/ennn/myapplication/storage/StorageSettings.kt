package com.ennn.myapplication.storage

import androidx.datastore.preferences.core.Preferences

fun mapSettingsPreferences(preferences: Preferences): SettingsPreferences {
    val ipAddress = preferences[PrefKeys.IP_ADDRESS]?.toString()?:""
    val portAddress = preferences[PrefKeys.PORT_ADDRESS]?.toString()?:""

    return SettingsPreferences(ipAddress, portAddress)
}

data class SettingsPreferences(val ipAddress: String, val portAddress: String)