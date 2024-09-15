package com.example.randomuserapp.ui.random

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.usecase.GetRandomUserUseCase
import com.example.randomuserapp.domain.usecase.InsertFavoriteUserUseCase
import com.example.randomuserapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomUserViewModel @Inject constructor(
    private val getRandomUserUseCase: GetRandomUserUseCase,
    private val insertFavoriteUserUseCase: InsertFavoriteUserUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    var state: MutableStateFlow<UiState> = _state

    init {
        getRandomUser()
    }

    fun getRandomUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(
                loading = false,
                favoriteUser = getRandomUserUseCase(),
                favoritesUserList = emptyList()
            )
        }
    }

    fun insertFavoriteUser(favoriteUser: RandomUserModel?) {
        if (favoriteUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                _state.value = _state.value.copy(
                    loading = false,
                    favoriteUser = favoriteUser,
                    favoritesUserList = emptyList()
                )
                insertFavoriteUserUseCase(favoriteUser)

            }
        }
    }
}