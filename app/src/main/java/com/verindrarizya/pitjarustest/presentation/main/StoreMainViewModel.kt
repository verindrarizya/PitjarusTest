package com.verindrarizya.pitjarustest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verindrarizya.pitjarustest.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreMainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val username: Flow<String?> = authRepository.username

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

}