package com.ennn.myapplication.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject constructor(private val networkService: NetworkService)  {
    suspend fun getUsersList() = withContext(Dispatchers.IO) {
        networkService.getUsersList()
    }
    suspend fun loginUser(userName: String, pass: String) = withContext(Dispatchers.IO) {
        networkService.login(userName, pass)
    }
}