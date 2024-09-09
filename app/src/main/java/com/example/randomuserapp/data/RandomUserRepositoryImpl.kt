package com.example.randomuserapp.data

import com.example.randomuserapp.data.network.RandomUserApiService
import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(private val apiService: RandomUserApiService): RandomUserRepository {
    override suspend fun getRandomUser(): List<RandomUserModel> {
        return apiService.getUser().toDomain()
    }
}