package com.example.randomuserapp.domain.usecase

import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.model.toDatabase
import javax.inject.Inject

class InsertFavoriteUserUseCase @Inject constructor(private val randomUserRepository: RandomUserRepository) {
    suspend operator fun invoke(favoriteUsersList: List<RandomUserModel>) =
        randomUserRepository.insertRandomFavoriteUser(favoriteUsers = favoriteUsersList.toDatabase())
}