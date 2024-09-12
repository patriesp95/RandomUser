package com.example.randomuserapp.data.network.response

import com.example.randomuserapp.domain.model.RandomUserModel
import com.google.gson.annotations.SerializedName

data class RandomUserResponse(
    @SerializedName("results") val results: List<RandomUser>
)

fun RandomUserResponse.responseToDomain(): List<RandomUserModel> {
    return listOf(
        RandomUserModel(
            image = this.results[0].picture.large,
            name = "${this.results[0].name.first} ${this.results[0].name.last}",
            country = this.results[0].location.country
        )
    )
}