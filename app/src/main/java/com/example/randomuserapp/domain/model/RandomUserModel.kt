package com.example.randomuserapp.domain.model

import com.example.randomuserapp.data.database.entities.RandomUserEntity

data class RandomUserModel(
    val image: String,
    val name: String,
    val country: String
)

fun RandomUserModel.toDatabase(): RandomUserEntity {
    return RandomUserEntity(
        image = image,
        name = name,
        country = country
    )
}