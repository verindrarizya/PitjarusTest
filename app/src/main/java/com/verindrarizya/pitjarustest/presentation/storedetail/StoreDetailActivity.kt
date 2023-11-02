package com.verindrarizya.pitjarustest.presentation.storedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.verindrarizya.pitjarustest.databinding.ActivityStoreDetailBinding

class StoreDetailActivity : AppCompatActivity() {

    private val binding: ActivityStoreDetailBinding by lazy {
        ActivityStoreDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}