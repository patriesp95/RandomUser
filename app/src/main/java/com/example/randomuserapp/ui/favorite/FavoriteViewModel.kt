package com.example.randomuserapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.usecase.GetFavoriteRandomUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val getFavoriteRandomUserUseCase: GetFavoriteRandomUserUseCase) :
    ViewModel() {

    private var _favoriteState = MutableStateFlow<List<RandomUserModel>>(emptyList())
    val favoriteState: MutableStateFlow<List<RandomUserModel>> get() = _favoriteState

    init {
        getFavoriteList()
    }

    fun getFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteState.value = getFavoriteRandomUserUseCase()
        }
    }
}