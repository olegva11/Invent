package com.ennn.myapplication.network

import com.ennn.myapplication.models.UsersLoginListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

    interface NetworkService {
        @GET("/user_list/")
        suspend fun getUsersList(
        ): UsersLoginListResponse

        @POST("/login")
        suspend fun login(
            @Query("username") userName: String,
            @Query("pass") pass: String,
        ): Response<Unit>
}