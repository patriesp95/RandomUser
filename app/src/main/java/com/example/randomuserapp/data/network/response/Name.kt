package com.example.randomuserapp.data.network.response

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)
