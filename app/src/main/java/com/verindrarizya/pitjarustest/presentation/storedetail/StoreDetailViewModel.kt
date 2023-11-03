package com.verindrarizya.pitjarustest.presentation.storedetail

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verindrarizya.pitjarustest.data.repository.auth.AuthRepository
import com.verindrarizya.pitjarustest.data.repository.store.StoreRepository
import com.verindrarizya.pitjarustest.presentation.model.Store
import com.verindrarizya.pitjarustest.util.Resource
import com.verindrarizya.pitjarustest.util.STORE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _store: MutableLiveData<Store> = MutableLiveData()
    val store: LiveData<Store>
        get() = _store

    private val _isWithinHundredMeterRadius: MutableLiveData<Boolean> = MutableLiveData()
    val isWithinHundredMeterRadius: LiveData<Boolean>
        get() = _isWithinHundredMeterRadius

    private val _imageUri: MutableLiveData<Uri?> = MutableLiveData(null)
    val imageUri: LiveData<Uri?>
        get() = _imageUri

    val isVisitEnabled = MediatorLiveData<Boolean>()

    private val _visitResult: MutableLiveData<Resource<String>> = MutableLiveData()
    val visitResult: LiveData<Resource<String>>
        get() = _visitResult

    val username: Flow<String?> = authRepository.username

    init {
        setUp()

        isVisitEnabled.addSource(_isWithinHundredMeterRadius) {
            isVisitEnabled.value = it && imageUri.value != null
        }
        isVisitEnabled.addSource(_imageUri) {
            isVisitEnabled.value = it != null && _isWithinHundredMeterRadius.value == true
        }
    }

    private fun setUp() {
        val storeId = savedStateHandle.get<Int>(STORE_ID)
        viewModelScope.launch {
            if (storeId != null) {
                storeRepository.getStore(storeId).collect {
                    _store.value = it
                }
            }
        }
    }

    fun updateWithinRadius(isWithin: Boolean) {
        _isWithinHundredMeterRadius.value = isWithin
    }

    fun setImageUri(newImageUri: Uri) {
        _imageUri.value = newImageUri
    }

    fun visit() {
        val newStore = store.value!!.copy(
            isVisited = true,
            imageUri = _imageUri.value!!
        )

        viewModelScope.launch {
            val result = storeRepository.updateStore(newStore)
            if (result is Resource.Success) {
                _visitResult.value = Resource.Success("Visit Success")
            } else {
                _visitResult.value = Resource.Failure("Visit Failed")
            }
        }
    }

}