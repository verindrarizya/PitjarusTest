package com.verindrarizya.pitjarustest.presentation.storelist

import androidx.lifecycle.ViewModel
import com.verindrarizya.pitjarustest.data.repository.auth.AuthRepository
import com.verindrarizya.pitjarustest.data.repository.store.StoreRepository
import com.verindrarizya.pitjarustest.presentation.model.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val stores: Flow<List<Store>> = storeRepository.getAllStore()

    val username: Flow<String?> = authRepository.username

}