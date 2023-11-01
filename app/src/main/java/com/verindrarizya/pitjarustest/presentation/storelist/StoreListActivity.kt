package com.verindrarizya.pitjarustest.presentation.storelist

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.verindrarizya.pitjarustest.databinding.ActivityStoreListBinding
import com.verindrarizya.pitjarustest.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreListActivity : AppCompatActivity() {

    private val binding: ActivityStoreListBinding by lazy {
        ActivityStoreListBinding.inflate(layoutInflater)
    }

    private val viewModel: StoreListViewModel by viewModels()

    private lateinit var storeItemAdapter: StoreItemAdapter

    private val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[fineLocationPermission] == true &&
            permissions[coarseLocationPermission] == true
        ) {
            showShortToast("Permission Granted")
            getCurrentLocation()
        } else {
            showShortToast("Accept to use this feature")
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkPermission()
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

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(
                arrayOf(
                    coarseLocationPermission,
                    fineLocationPermission
                )
            )
        } else {
            getCurrentLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) {
                showShortToast("Please turn on location")
            } else {
                // Set Marker
            }
        }
    }
}