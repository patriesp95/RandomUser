package com.example.randomuserapp.data.network.response

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country") val country: String
)
