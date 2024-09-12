package com.example.randomuserapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomuserapp.domain.model.RandomUserModel

@Entity(tableName = "favorite_user_table")
data class RandomUserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("country") val country: String
)

fun List<RandomUserEntity>.entityToDomain(): List<RandomUserModel> {
    return listOf(
        RandomUserModel(
            image = this[0].image,
            name = this[0].name,
            country = this[0].country
        )
    )
}