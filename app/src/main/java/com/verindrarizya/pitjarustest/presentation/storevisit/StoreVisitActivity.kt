package com.verindrarizya.pitjarustest.presentation.storevisit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.verindrarizya.pitjarustest.databinding.ActivityStoreVisitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreVisitActivity : AppCompatActivity() {

    private val binding: ActivityStoreVisitBinding by lazy {
        ActivityStoreVisitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}