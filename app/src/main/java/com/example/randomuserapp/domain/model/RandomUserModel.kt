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

fun List<RandomUserModel>.toDatabase(): List<RandomUserEntity> {
    return listOf(
        RandomUserEntity(
            image = this[0].image,
            name =  this[0].name,
            country =  this[0].country
        )
    )
}