package com.ennn.myapplication.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ennn.myapplication.data.Repository
import com.ennn.myapplication.storage.SettingsPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _ipSettings = MutableStateFlow("")
    val ipAddress: StateFlow<String> = _ipSettings
    private val _portSettings = MutableStateFlow("")
    val portAddress: StateFlow<String> = _portSettings
    private val _nameDatabaseSettings = MutableStateFlow("")
    val nameDatabaseSettings: StateFlow<String> = _nameDatabaseSettings
    private val _loginDatabaseSettings = MutableStateFlow("")
    val loginDatabaseSettings: StateFlow<String> = _loginDatabaseSettings
    private val _passwordDatabaseSettings = MutableStateFlow("")
    val passwordDatabaseSettings: StateFlow<String> = _passwordDatabaseSettings
    private val _serverKeyDatabaseSettings = MutableStateFlow("")
    val serverKeyDatabaseSettings: StateFlow<String> = _serverKeyDatabaseSettings

    private val _isErrorSetting = MutableStateFlow(false)
    val isErrorSetting: StateFlow<Boolean> = _isErrorSetting

    var ipAddressField = ""
    var portAddressField = ""
    var nameDatabaseField = ""
    var loginDatabaseField = ""
    var passwordDatabaseField = ""
    var serverKeyField = ""

    init {
        viewModelScope.launch {
            repository.getSettings().collect {
                _ipSettings.value = it.ipAddress
                _portSettings.value = it.portAddress
                _nameDatabaseSettings.value = it.nameDatabase
                _loginDatabaseSettings.value = it.loginDatabase
                _passwordDatabaseSettings.value = it.passwordDatabase
                _serverKeyDatabaseSettings.value = it.serverKeyDatabase

                ipAddressField = it.ipAddress
                portAddressField = it.portAddress
                nameDatabaseField = it.nameDatabase
                loginDatabaseField = it.loginDatabase
                passwordDatabaseField = it.passwordDatabase
                serverKeyField = it.serverKeyDatabase
            }
        }
    }

    fun isError(ipError: Boolean, portError: Boolean) {
        _isErrorSetting.value = ipError || portError
    }

    fun saveConnectSettings() {
        viewModelScope.launch {
            repository.writeSettings(
                SettingsPreferences(
                    ipAddressField,
                    portAddressField,
                    nameDatabaseField,
                    loginDatabaseField,
                    passwordDatabaseField,
                    serverKeyField
                )
            )
        }
    }
}