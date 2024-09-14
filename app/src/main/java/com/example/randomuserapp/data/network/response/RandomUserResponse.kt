package com.example.randomuserapp.data.network.response

import com.example.randomuserapp.domain.model.RandomUserModel
import com.google.gson.annotations.SerializedName

data class RandomUserResponse(
    @SerializedName("results") val results: List<RandomUser>
)

fun RandomUserResponse.responseToDomain(): RandomUserModel {
    return RandomUserModel(
        image = results[0].picture.large,
        name = "${results[0].name.first} ${results[0].name.last}",
        country = results[0].location.country
    )
}