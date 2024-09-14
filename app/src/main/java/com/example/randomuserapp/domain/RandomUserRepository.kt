package com.example.randomuserapp.domain

import com.example.randomuserapp.data.database.entities.RandomUserEntity
import com.example.randomuserapp.domain.model.RandomUserModel
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {
    val favoriteUserList: Flow<List<RandomUserModel>>
    suspend fun getRandomUserFromApi(): RandomUserModel
    suspend fun insertRandomFavoriteUser(favoriteUser: RandomUserEntity)
    suspend fun clearRandomFavoriteUser()
}