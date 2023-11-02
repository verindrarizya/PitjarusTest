package com.verindrarizya.pitjarustest.presentation.storevisit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.verindrarizya.pitjarustest.databinding.ActivityStoreVisitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreVisitActivity : AppCompatActivity() {

    private val binding: ActivityStoreVisitBinding by lazy {
        ActivityStoreVisitBinding.inflate(layoutInflater)
    }

    private val viewModel: StoreVisitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpObserver()

        binding.btnSelesai.setOnClickListener { finish() }
    }

    private fun setUpObserver() {
        viewModel.store.observe(this) {
            Glide.with(this)
                .load(it.imageUri)
                .circleCrop()
                .into(binding.ivStore)

            binding.tvStoreName.text = it.storeName
        }
    }
}