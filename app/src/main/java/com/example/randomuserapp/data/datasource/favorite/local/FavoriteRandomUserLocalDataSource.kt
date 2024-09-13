package com.example.randomuserapp.data.datasource.favorite.local

import com.example.randomuserapp.domain.model.RandomUserModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRandomUserLocalDataSource {

    val favoriteRandomUsersList: Flow<List<RandomUserModel>>

    fun isEmpty(): Boolean

    suspend fun save(favoriteRandomUsersList: List<RandomUserModel>)

    suspend fun clear()
}