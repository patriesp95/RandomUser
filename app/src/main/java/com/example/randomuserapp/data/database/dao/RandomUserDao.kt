package com.example.randomuserapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuserapp.data.database.entities.RandomUserEntity

@Dao
interface RandomUserDao {
    @Query("SELECT * FROM favorite_user_table ORDER BY country DESC")
    suspend fun getAllFavoriteUsers():List<RandomUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users:List<RandomUserEntity>)

    @Query("DELETE FROM favorite_user_table")
    suspend fun deleteAllRandomFavoriteUsers()
}
