package com.example.randomuserapp.data.network

import com.example.randomuserapp.data.network.response.RandomUserResponse
import retrofit2.http.GET

interface RandomUserApiService {

    @GET("/api/")
    suspend fun getUser():RandomUserResponse
}