package com.example.randomuserapp.data

import com.example.randomuserapp.data.database.entities.RandomUserEntity
import com.example.randomuserapp.data.database.entities.entityToDomain
import com.example.randomuserapp.data.datasource.favorite.local.FavoriteRandomUserLocalDataSource
import com.example.randomuserapp.data.datasource.random.remote.RandomUserRemoteDataSource
import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val randomUserRemoteDataSource: RandomUserRemoteDataSource,
    private val favoriteRandomUserLocalDataSource: FavoriteRandomUserLocalDataSource
) : RandomUserRepository {

    override val favoriteUserList = favoriteRandomUserLocalDataSource.favoriteRandomUserList

    override suspend fun getRandomUserFromApi(): RandomUserModel {
        return randomUserRemoteDataSource.getRandomUserFromApi()
    }

    override suspend fun insertRandomFavoriteUser(favoriteUser: RandomUserEntity) {
        favoriteRandomUserLocalDataSource.save(favoriteUser.entityToDomain())
    }

    override suspend fun clearRandomFavoriteUser() {
        favoriteRandomUserLocalDataSource.clear()
    }
}

