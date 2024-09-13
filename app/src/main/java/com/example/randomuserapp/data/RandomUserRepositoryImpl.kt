package com.example.randomuserapp.data

import com.example.randomuserapp.data.database.entities.RandomUserEntity
import com.example.randomuserapp.data.database.entities.entityToDomain
import com.example.randomuserapp.data.datasource.favorite.local.FavoriteRandomUserLocalDataSource
import com.example.randomuserapp.data.datasource.random.remote.RandomUserRemoteDataSource
import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.model.toDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val randomUserRemoteDataSource: RandomUserRemoteDataSource,
    private val favoriteRandomUserLocalDataSource: FavoriteRandomUserLocalDataSource
) : RandomUserRepository {

    override val favoriteUserList = favoriteRandomUserLocalDataSource.favoriteRandomUsersList

    override suspend fun getRandomUserFromApi(): List<RandomUserModel> {
        return randomUserRemoteDataSource.getRandomUserFromApi()
    }

    override suspend fun insertRandomFavoriteUser(favoriteUsers: List<RandomUserEntity>) {
        favoriteRandomUserLocalDataSource.save(favoriteUsers.entityToDomain())
    }

    override suspend fun clearRandomFavoriteUser() {
        TODO("Not yet implemented")
    }
}

