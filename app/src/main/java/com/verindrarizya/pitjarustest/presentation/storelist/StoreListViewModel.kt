package com.verindrarizya.pitjarustest.presentation.storelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.verindrarizya.pitjarustest.data.repository.store.StoreRepository
import com.verindrarizya.pitjarustest.presentation.model.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    val stores: LiveData<List<Store>> = storeRepository.getAllStore()

}