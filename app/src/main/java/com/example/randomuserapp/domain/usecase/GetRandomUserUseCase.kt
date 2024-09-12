package com.example.randomuserapp.domain.usecase

import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(private val randomUserRepository: RandomUserRepository) {
    suspend operator fun invoke() = randomUserRepository.getRandomUserFromApi()
}