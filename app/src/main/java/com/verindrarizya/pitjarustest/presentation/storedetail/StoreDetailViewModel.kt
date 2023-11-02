package com.verindrarizya.pitjarustest.presentation.storedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.verindrarizya.pitjarustest.data.repository.store.StoreRepository
import com.verindrarizya.pitjarustest.presentation.model.Store
import com.verindrarizya.pitjarustest.util.STORE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _store: MutableLiveData<Store> = MutableLiveData()
    val store: LiveData<Store>
        get() = _store

    private val _isWithinHundredMeterRadius: MutableLiveData<Boolean> = MutableLiveData()
    val isWithinHundredMeterRadius: LiveData<Boolean>
        get() = _isWithinHundredMeterRadius


    init {
        setUp()
    }

    private fun setUp() {
        val store = savedStateHandle.get<Store>(STORE)
        store?.let {
            _store.value = it
        }
    }

    fun updateWithinRadius(isWithin: Boolean) {
        _isWithinHundredMeterRadius.value = isWithin
    }

}