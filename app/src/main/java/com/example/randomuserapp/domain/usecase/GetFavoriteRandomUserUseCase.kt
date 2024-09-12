package com.example.randomuserapp.domain.usecase

import android.util.Log
import com.example.randomuserapp.domain.RandomUserRepository
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.model.toDatabase
import javax.inject.Inject

class GetFavoriteRandomUserUseCase @Inject constructor(private val randomUserRepository: RandomUserRepository) {

        suspend operator fun invoke():List<RandomUserModel> {
        val randomUserFavoriteList = randomUserRepository.getRandomUserFromApi()

        return if(randomUserFavoriteList.isNotEmpty()){
            randomUserRepository.clearRandomFavoriteUser()
            randomUserRepository.insertRandomFavoriteUser(randomUserFavoriteList.toDatabase())
            Log.d("patri", randomUserFavoriteList.toString())
            randomUserFavoriteList
        }else{
            randomUserRepository.getRandomUserFromDatabase()
        }
    }

}