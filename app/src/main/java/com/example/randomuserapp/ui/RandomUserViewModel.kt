package com.example.randomuserapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.domain.usecase.GetRandomUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomUserViewModel @Inject constructor(
    private val getRandomUserUseCase: GetRandomUserUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<List<RandomUserModel>>(emptyList())
    val state: MutableStateFlow<List<RandomUserModel>> get() = _state

    private var _loading = MutableStateFlow<Boolean>(true)
    val loading:  MutableStateFlow<Boolean> get() = _loading


    init {
        getRandomUser()
    }

    fun getRandomUser() {
        viewModelScope.launch {
            _loading.value = true
            _state.value = getRandomUserUseCase()
            _loading.value = false
        }
    }
}