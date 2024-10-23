package com.ennn.myapplication.storage

import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {
    val IP_ADDRESS = stringPreferencesKey("ip_address")
    val PORT_ADDRESS = stringPreferencesKey("port_address")
    val NAME_DATABASE = stringPreferencesKey("name_database")
    val LOGIN_DATABASE = stringPreferencesKey("login_database")
    val PASSWORD_DATABASE = stringPreferencesKey("password_database")
    val SERVER_KEY_DATABASE = stringPreferencesKey("server_key_database")
}
