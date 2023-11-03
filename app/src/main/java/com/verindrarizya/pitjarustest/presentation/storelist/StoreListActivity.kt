package com.verindrarizya.pitjarustest.presentation.storelist

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationTokenSource
import com.verindrarizya.pitjarustest.R
import com.verindrarizya.pitjarustest.databinding.ActivityStoreListBinding
import com.verindrarizya.pitjarustest.presentation.storedetail.StoreDetailActivity
import com.verindrarizya.pitjarustest.util.STORE_ID
import com.verindrarizya.pitjarustest.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StoreListActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding: ActivityStoreListBinding by lazy {
        ActivityStoreListBinding.inflate(layoutInflater)
    }

    private val viewModel: StoreListViewModel by viewModels()

    private lateinit var storeItemAdapter: StoreItemAdapter

    private val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION

    @Inject
    lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var map: GoogleMap

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[fineLocationPermission] == true &&
            permissions[coarseLocationPermission] == true
        ) {
            showShortToast("Permission Granted")
            setUpMap()
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
        storeItemAdapter = StoreItemAdapter {
            val intent = Intent(this, StoreDetailActivity::class.java)
            intent.putExtra(STORE_ID, it.id)
            startActivity(intent)
        }
        binding.rvStore.layoutManager = LinearLayoutManager(this)
        binding.rvStore.adapter = storeItemAdapter
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stores.collect {
                    storeItemAdapter.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.username.collect {
                    if (it != null) {
                        binding.toolbar.subtitle = it
                    }
                }
            }
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
            setUpMap()
        }
    }

    private fun setUpMap() {
        val mapFragment =
            supportFragmentManager.findFragmentById(binding.maps.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
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
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { location ->
                if (location == null) {
                    showShortToast("Please turn on location")
                } else {
                    val locationLatLng = LatLng(location.latitude, location.longitude)
                    map.addMarker(
                        MarkerOptions()
                            .position(locationLatLng)
                            .title("You're Here")
                    )

                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, 10f))

                    lifecycleScope.launch {
                        val stores = viewModel.stores.first()
                        stores.forEach { store ->
                            val storeLatLng =
                                LatLng(store.latitude.toDouble(), store.longitude.toDouble())
                            map.addMarker(
                                MarkerOptions()
                                    .position(storeLatLng)
                                    .title(store.storeName)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_map))
                            )
                        }
                    }
                }
            }
        }
    }
}