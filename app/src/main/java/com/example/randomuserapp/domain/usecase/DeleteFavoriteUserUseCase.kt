package com.example.randomuserapp.domain.usecase

import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.model.toDatabase
import javax.inject.Inject

class DeleteFavoriteUserUseCase @Inject constructor(private val randomUserRepository: RandomUserRepository) {
    operator fun invoke(favoriteUser: RandomUserModel) =
        randomUserRepository.clearRandomFavoriteUser(favoriteUser = favoriteUser.toDatabase())
}