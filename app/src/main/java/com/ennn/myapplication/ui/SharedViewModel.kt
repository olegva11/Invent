package com.ennn.myapplication.ui

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
class SharedViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _ipSettings = MutableStateFlow("")
    val ipAddress: StateFlow<String> = _ipSettings
    private val _portSettings = MutableStateFlow("")
    val portAddress: StateFlow<String> = _portSettings

    private val _isErrorSetting = MutableStateFlow(false)
    val isErrorSetting: StateFlow<Boolean> = _isErrorSetting

    var ipAddressField = ""
    var portAddressField = ""

    init {
        viewModelScope.launch {
            repository.getSettings().collect {
                _ipSettings.value = it.ipAddress
                _portSettings.value = it.portAddress

                ipAddressField = it.ipAddress
                portAddressField = it.portAddress
            }
        }
    }

    fun isError(ipError: Boolean, portError: Boolean)
    {
        _isErrorSetting.value = ipError || portError
    }

    fun saveConnectSettings() {
        viewModelScope.launch {
            repository.writeSettings(SettingsPreferences(ipAddressField, portAddressField))
        }
    }
}