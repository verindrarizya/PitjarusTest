package com.verindrarizya.pitjarustest.presentation.storevisit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.verindrarizya.pitjarustest.R
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

        setUpRecyclerView()
        setUpObserver()

        binding.btnSelesai.setOnClickListener { finish() }
    }

    private lateinit var dashboardStoreItemAdapter: DashboardStoreItemAdapter

    private fun setUpObserver() {
        viewModel.store.observe(this) {
            Glide.with(this)
                .load(it.imageUri)
                .circleCrop()
                .into(binding.ivStore)

            val icPerfectStoreStatus = if (it.isVisited) {
                ContextCompat.getDrawable(this, R.drawable.ic_check)
            } else {
                ContextCompat.getDrawable(this, R.drawable.ic_cancel)
            }

            binding.tvPerfectStoreStatus.setCompoundDrawablesRelativeWithIntrinsicBounds(
                icPerfectStoreStatus, null, null, null
            )

            binding.tvStoreName.text = it.storeName
        }

        viewModel.dashboardStoreItems.observe(this) {
            dashboardStoreItemAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        dashboardStoreItemAdapter = DashboardStoreItemAdapter()
        binding.rvDashboardStoreItem.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDashboardStoreItem.adapter = dashboardStoreItemAdapter
    }
}