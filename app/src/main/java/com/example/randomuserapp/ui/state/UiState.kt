package com.example.randomuserapp.ui.state

import com.example.randomuserapp.domain.model.RandomUserModel

data class UiState(
    val loading: Boolean = false,
    val favoriteUser: RandomUserModel? = null,
    val favoritesUserList: List<RandomUserModel> = emptyList()
)