package com.example.randomuserapp.di

import com.example.randomuserapp.data.RandomUserRepositoryImpl
import com.example.randomuserapp.data.database.dao.RandomUserDao
import com.example.randomuserapp.data.network.RandomUserApiService
import com.example.randomuserapp.domain.RandomUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun provideRandomUserApiService(retrofit: Retrofit): RandomUserApiService {
        return retrofit.create(RandomUserApiService::class.java);
    }

    @Provides
    fun provideRandomUserRepository(apiService: RandomUserApiService, dao: RandomUserDao):RandomUserRepository{
        return RandomUserRepositoryImpl(apiService, dao)
    }
}