package com.example.randomuserapp.data.network.response

import com.example.randomuserapp.domain.model.RandomUserModel
import com.google.gson.annotations.SerializedName

data class RandomUser(
    @SerializedName("name") val name: Name,
    @SerializedName("location") val location: Location,
    @SerializedName("picture") val picture: Picture
)