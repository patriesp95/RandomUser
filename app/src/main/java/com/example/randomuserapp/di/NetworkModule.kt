package com.example.randomuserapp.di

import com.example.randomuserapp.data.RandomUserRepositoryImpl
import com.example.randomuserapp.data.network.RandomUserApiService
import com.example.randomuserapp.domain.RandomUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Provides
    fun provideRandomUserApiService(retrofit: Retrofit): RandomUserApiService {
        return retrofit.create(RandomUserApiService::class.java);
    }

    @Provides
    fun provideRandomUserRepository(apiService: RandomUserApiService):RandomUserRepository{
        return RandomUserRepositoryImpl(apiService)
    }
}