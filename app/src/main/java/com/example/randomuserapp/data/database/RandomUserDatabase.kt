package com.example.randomuserapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomuserapp.data.database.dao.RandomUserDao
import com.example.randomuserapp.data.database.entities.RandomUserEntity

@Database(entities = [RandomUserEntity::class], version = 1)
abstract class RandomUserDatabase: RoomDatabase() {

    abstract fun getRandomUserDao():RandomUserDao
}