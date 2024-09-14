package com.example.randomuserapp.data.datasource.favorite.local

import com.example.randomuserapp.domain.model.RandomUserModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRandomUserLocalDataSource {

    val favoriteRandomUser: Flow<RandomUserModel>

    suspend fun save(favoriteRandomUser: RandomUserModel)

    suspend fun clear()
}