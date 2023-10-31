package com.verindrarizya.pitjarustest.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verindrarizya.pitjarustest.data.repository.auth.AuthRepository
import com.verindrarizya.pitjarustest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginResource = MutableLiveData<Resource<String>>()
    val loginResource: LiveData<Resource<String>>
        get() = _loginResource

    fun login(username: String, password: String) {
        Log.d("PitTag", "login: viewmodel called")
        viewModelScope.launch {
            authRepository.login(username, password).collect {
                _loginResource.value = it
            }
        }
    }
}