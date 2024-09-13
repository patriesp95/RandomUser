package com.example.randomuserapp.data.datasource.random.remote

import com.example.randomuserapp.data.network.RandomUserApiService
import com.example.randomuserapp.data.network.response.responseToDomain
import com.example.randomuserapp.domain.model.RandomUserModel
import javax.inject.Inject

class RandomUserRemoteDataSourceImpl @Inject constructor(private val apiService: RandomUserApiService) :
    RandomUserRemoteDataSource {
    override suspend fun getRandomUserFromApi(): List<RandomUserModel> {
        return apiService.getUser().responseToDomain()
    }
}