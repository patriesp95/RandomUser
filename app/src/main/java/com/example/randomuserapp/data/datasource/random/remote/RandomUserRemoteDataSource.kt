package com.example.randomuserapp.data.datasource.random.remote

import com.example.randomuserapp.domain.model.RandomUserModel

interface RandomUserRemoteDataSource {
    suspend fun getRandomUserFromApi(): List<RandomUserModel>
}