package com.verindrarizya.pitjarustest.presentation.storedetail

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.verindrarizya.pitjarustest.databinding.ActivityStoreDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StoreDetailActivity : AppCompatActivity() {

    private val binding: ActivityStoreDetailBinding by lazy {
        ActivityStoreDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: StoreDetailViewModel by viewModels()

    @Inject
    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.store.observe(this) { store ->
            binding.tvStoreName.text = store.storeName

            checkDistance(store.latitude.toDouble(), store.longitude.toDouble())

            binding.btnReset.setOnClickListener {
                binding.tvLocationStatus.text = "Loading..."
                checkDistance(store.latitude.toDouble(), store.longitude.toDouble())
            }
        }

        viewModel.isWithinHundredMeterRadius.observe(this) { isWithin ->
            binding.tvLocationStatus.text = if (isWithin) {
                "Lokasi Sesuai"
            } else {
                "Lokasi Belum Sesuai"
            }
        }
    }

    // Saya suppress tanpa check permission karena untuk masuk ke activity ini
    // di activity list harus granted permission terlebih dahulu, jikalau tidak maka
    // activity tidak akan ke-reach
    @SuppressLint("MissingPermission")
    private fun checkDistance(storeLat: Double, storeLng: Double) {
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { location ->
            location?.let {
                val radius = 100.0
                val results = FloatArray(1)

                Location.distanceBetween(
                    location.latitude,
                    location.longitude,
                    storeLat,
                    storeLng,
                    results
                )

                if (results[0] < radius) {
                    viewModel.updateWithinRadius(true)
                } else {
                    viewModel.updateWithinRadius(false)
                }
            }
        }
    }
}