package com.verindrarizya.pitjarustest.presentation.storelist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.verindrarizya.pitjarustest.databinding.ActivityStoreListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreListActivity : AppCompatActivity() {

    private val binding: ActivityStoreListBinding by lazy {
        ActivityStoreListBinding.inflate(layoutInflater)
    }

    private val viewModel: StoreListViewModel by viewModels()

    private lateinit var storeItemAdapter: StoreItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpToolbarListener()
        setUpStoreRecyclerView()
        setUpObserver()
    }

    private fun setUpToolbarListener() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpStoreRecyclerView() {
        storeItemAdapter = StoreItemAdapter { }
        binding.rvStore.layoutManager = LinearLayoutManager(this)
        binding.rvStore.adapter = storeItemAdapter
    }

    private fun setUpObserver() {
        viewModel.stores.observe(this) {
            storeItemAdapter.submitList(it)
        }
    }
}