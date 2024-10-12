package com.ennn.myapplication.storage

import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {
    val IP_ADDRESS = stringPreferencesKey("ip_address")
    val PORT_ADDRESS = stringPreferencesKey("port_address")
}
