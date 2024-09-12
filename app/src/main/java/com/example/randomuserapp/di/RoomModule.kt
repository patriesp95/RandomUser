package com.example.randomuserapp.di

import android.content.Context
import androidx.room.Room
import com.example.randomuserapp.data.database.RandomUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val RANDOM_USER_DATABASE_NAME = "random_user_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RandomUserDatabase::class.java, RANDOM_USER_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRandomUserDao(db:RandomUserDatabase) = db.getRandomUserDao()
}