package com.example.randomuserapp.data.datasource.favorite.local

import com.example.randomuserapp.domain.model.RandomUserModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRandomUserLocalDataSource {

    val favoriteRandomUserList: Flow<List<RandomUserModel>>

    suspend fun save(favoriteRandomUser: RandomUserModel)

    suspend fun clear()

    fun deleteUser(favoriteRandomUser: RandomUserModel)
}