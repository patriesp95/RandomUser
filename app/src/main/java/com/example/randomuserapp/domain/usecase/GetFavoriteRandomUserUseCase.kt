package com.example.randomuserapp.domain.usecase

import com.example.randomuserapp.domain.RandomUserRepository
import javax.inject.Inject

class GetFavoriteRandomUserUseCase @Inject constructor(private val randomUserRepository: RandomUserRepository) {

    operator fun invoke() = randomUserRepository.favoriteUserList

}