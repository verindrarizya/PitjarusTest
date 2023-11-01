package com.verindrarizya.pitjarustest.data.repository.store

import androidx.lifecycle.LiveData
import com.verindrarizya.pitjarustest.presentation.model.Store

interface StoreRepository {

    fun getAllStore(): LiveData<List<Store>>

}