package com.example.randomuserapp.data

import com.example.randomuserapp.data.database.dao.RandomUserDao
import com.example.randomuserapp.data.database.entities.RandomUserEntity
import com.example.randomuserapp.data.database.entities.entityToDomain
import com.example.randomuserapp.data.network.RandomUserApiService
import com.example.randomuserapp.data.network.response.responseToDomain
import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(private val apiService: RandomUserApiService,
    private val randomUserDao: RandomUserDao): RandomUserRepository {

    override suspend fun getRandomUserFromApi(): List<RandomUserModel> {
        return apiService.getUser().responseToDomain()
    }

    override suspend fun getRandomUserFromDatabase():List<RandomUserModel>{
        return randomUserDao.getAllFavoriteUsers().entityToDomain()
    }

    override suspend fun insertRandomFavoriteUser(randomUserFavoriteList: List<RandomUserEntity>) {
        randomUserDao.insertAll(randomUserFavoriteList)
    }

    override suspend fun clearRandomFavoriteUser() {
        randomUserDao.deleteAllRandomFavoriteUsers()
    }
}
