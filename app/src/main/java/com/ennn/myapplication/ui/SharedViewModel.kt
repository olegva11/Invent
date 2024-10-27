package com.ennn.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ennn.myapplication.data.Repository
import com.ennn.myapplication.models.UsersLoginListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val queryResponse = MutableStateFlow("")
    private val _error = MutableStateFlow(0)
    val error: StateFlow<Int> = _error
    private val _userList = MutableStateFlow(UsersLoginListResponse())
    val userList: StateFlow<UsersLoginListResponse> = _userList

    init {
        viewModelScope.launch {
         //   _userList.value = repository.getUsersListFromServer()
        }
    }

    fun loginServer(userName: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO)
        {
            val results = repository.loginUserServer(userName, pass)
            val code = results.code()
            if (code == 404) {
                _error.value = code
            } else if (code == 200) {
                queryResponse.value = results.body().toString()
            }

        }
    }
}