package com.example.randomuserapp.data.network

import com.example.randomuserapp.data.network.response.RandomUserResponse
import com.example.randomuserapp.domain.model.RandomUserModel
import retrofit2.http.GET

interface RandomUserApiService {

    @GET("/api/")
    suspend fun getUser():RandomUserResponse
}