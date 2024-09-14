package com.example.randomuserapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuserapp.data.database.entities.RandomUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomUserDao {
    @Query("SELECT * FROM favorite_user_table ORDER BY country DESC")
    fun getAllFavoriteUsers(): Flow<List<RandomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: RandomUserEntity)

    @Query("DELETE FROM favorite_user_table")
    suspend fun deleteAllRandomFavoriteUsers()
}
