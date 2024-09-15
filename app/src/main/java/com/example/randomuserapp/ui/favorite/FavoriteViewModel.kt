package com.example.randomuserapp.ui.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.usecase.DeleteFavoriteUserUseCase
import com.example.randomuserapp.domain.usecase.GetFavoriteRandomUserUseCase
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
class FavoriteViewModel @Inject constructor(
    private val getFavoriteRandomUserUseCase: GetFavoriteRandomUserUseCase,
    private val insertFavoriteUserUseCase: InsertFavoriteUserUseCase,
    private val deleteFavoriteUserUseCase: DeleteFavoriteUserUseCase
) :
    ViewModel() {

    private var _favoriteState = MutableStateFlow(UiState())
    val favoriteState: StateFlow<UiState> = _favoriteState.asStateFlow()

    init {
        getFavoriteList()
    }

    private fun getFavoriteList() {
        viewModelScope.launch {
            getFavoriteRandomUserUseCase().collect { favoriteUserList ->
                _favoriteState.value = _favoriteState.value.copy(
                    loading = false,
                    favoriteUser = null,
                    favoritesUserList = favoriteUserList
                )
            }
        }
    }

    fun deleteFavoriteUser(favoriteUser: RandomUserModel){
        deleteFavoriteUserUseCase(favoriteUser)
    }
}