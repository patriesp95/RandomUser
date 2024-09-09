package com.example.randomuserapp.domain

import com.example.randomuserapp.domain.model.RandomUserModel

interface RandomUserRepository {
    suspend fun getRandomUser(): List<RandomUserModel>
}