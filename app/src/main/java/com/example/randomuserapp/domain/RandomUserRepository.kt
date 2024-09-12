package com.example.randomuserapp.domain

import com.example.randomuserapp.data.database.entities.RandomUserEntity
import com.example.randomuserapp.domain.model.RandomUserModel

interface RandomUserRepository {
    suspend fun getRandomUserFromApi(): List<RandomUserModel>
    suspend fun getRandomUserFromDatabase(): List<RandomUserModel>
    suspend fun insertRandomFavoriteUser(randomUserFavoriteList: List<RandomUserEntity>)
    suspend fun clearRandomFavoriteUser()
}