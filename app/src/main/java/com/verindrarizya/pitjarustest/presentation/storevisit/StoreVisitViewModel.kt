package com.verindrarizya.pitjarustest.presentation.storevisit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verindrarizya.pitjarustest.data.repository.store.StoreRepository
import com.verindrarizya.pitjarustest.presentation.model.DashboardStoreItem
import com.verindrarizya.pitjarustest.presentation.model.Store
import com.verindrarizya.pitjarustest.presentation.model.dashboardStoreItemsPlaceholder
import com.verindrarizya.pitjarustest.util.STORE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreVisitViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _store: MutableLiveData<Store> = MutableLiveData()
    val store: LiveData<Store>
        get() = _store

    private val _dashboardStoreItems: MutableLiveData<List<DashboardStoreItem>> = MutableLiveData()
    val dashboardStoreItems: LiveData<List<DashboardStoreItem>>
        get() = _dashboardStoreItems

    init {
        setUp()
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

        _dashboardStoreItems.value = dashboardStoreItemsPlaceholder
    }

}