package com.example.randomuserapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.usecase.GetFavoriteRandomUserUseCase
import com.example.randomuserapp.domain.usecase.InsertFavoriteUserUseCase
import com.example.randomuserapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteRandomUserUseCase: GetFavoriteRandomUserUseCase,
    private val insertFavoriteUserUseCase: InsertFavoriteUserUseCase,
) :
    ViewModel() {

    private var _favoriteState = MutableStateFlow(UiState())
    val favoriteState: StateFlow<UiState> = _favoriteState.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteRandomUserUseCase().collect { favoriteUser ->
                _favoriteState.update { UiState(favoriteUser = favoriteUser) }
            }
        }
        getFavoriteList()
    }

    fun getFavoriteList() {
        viewModelScope.launch {
            _favoriteState.value = _favoriteState.value.copy(loading = true)
            getFavoriteRandomUserUseCase()
            _favoriteState.value = _favoriteState.value.copy(loading = false)
        }
    }

    suspend fun insertFavoriteUser(favoriteUser: RandomUserModel?) {
        if (favoriteUser != null) {
            insertFavoriteUserUseCase(favoriteUser)
        }
    }
}