package com.example.randomuserapp.ui.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.domain.usecase.GetRandomUserUseCase
import com.example.randomuserapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomUserViewModel @Inject constructor(
    private val getRandomUserUseCase: GetRandomUserUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        getRandomUser()
    }

    fun getRandomUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(loading = true)
            _state.value = _state.value.copy(favoriteUser = getRandomUserUseCase())
            _state.value = _state.value.copy(loading = false)
        }
    }
}