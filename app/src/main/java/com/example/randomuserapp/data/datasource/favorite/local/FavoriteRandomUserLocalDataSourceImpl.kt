package com.example.randomuserapp.data.datasource.favorite.local

import com.example.randomuserapp.data.database.dao.RandomUserDao
import com.example.randomuserapp.data.database.entities.entityToDomain
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.model.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRandomUserLocalDataSourceImpl @Inject constructor(private val dao: RandomUserDao) :
    FavoriteRandomUserLocalDataSource {

    override val favoriteRandomUsersList: Flow<List<RandomUserModel>> =
        dao.getAllFavoriteUsers().map {
            it.entityToDomain()
        }

    override fun isEmpty(): Boolean = dao.favoriteUserCount() == 0

    override suspend fun save(favoriteRandomUsersList: List<RandomUserModel>) {
        dao.insertAll(favoriteRandomUsersList.toDatabase())
    }

    override suspend fun clear() {
        dao.deleteAllRandomFavoriteUsers()
    }

}