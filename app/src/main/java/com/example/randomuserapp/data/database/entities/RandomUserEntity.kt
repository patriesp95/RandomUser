package com.example.randomuserapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.randomuserapp.data.network.response.RandomUser
import com.example.randomuserapp.domain.model.RandomUserModel

@Entity(tableName = "favorite_user_table", primaryKeys = ["name", "image", "country"])
data class RandomUserEntity(
    @ColumnInfo("image") val image: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("country") val country: String
)

fun RandomUserEntity.entityToDomain(): RandomUserModel {
    return RandomUserModel(
        image = image,
        name = name,
        country = country
    )
}

fun List<RandomUserEntity>.entityToDomain(): List<RandomUserModel> {
    val myList = this.map {
        RandomUserModel(
            image = it.image,
            name = it.name,
            country = it.country
        )
    }
    return myList
}