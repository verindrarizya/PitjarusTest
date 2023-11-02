package com.verindrarizya.pitjarustest.presentation.storedetail

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.verindrarizya.pitjarustest.databinding.ActivityStoreDetailBinding
import com.verindrarizya.pitjarustest.presentation.storevisit.StoreVisitActivity
import com.verindrarizya.pitjarustest.util.Resource
import com.verindrarizya.pitjarustest.util.STORE_ID
import com.verindrarizya.pitjarustest.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class StoreDetailActivity : AppCompatActivity() {

    private val binding: ActivityStoreDetailBinding by lazy {
        ActivityStoreDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: StoreDetailViewModel by viewModels()

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                viewModel.setImageUri(imageUri)
            }
        }

    private lateinit var imageUri: Uri

    @Inject
    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpToolbar()
        setUpObserver()
        setUpButtonCamera()

        binding.btnNoVisit.setOnClickListener { onBackPressed() }
        binding.btnVisit.setOnClickListener { viewModel.visit() }
    }

    private fun setUpObserver() {
        viewModel.store.observe(this) { store ->
            binding.tvStoreName.text = store.storeName

            checkDistance(store.latitude.toDouble(), store.longitude.toDouble())

            binding.btnReset.setOnClickListener {
                binding.tvLocationStatus.text = "Loading..."
                checkDistance(store.latitude.toDouble(), store.longitude.toDouble())
            }

            store.imageUri?.let {
                Glide.with(this)
                    .load(it)
                    .centerCrop()
                    .into(binding.ivStore)
            }
        }

        viewModel.isWithinHundredMeterRadius.observe(this) { isWithin ->
            binding.tvLocationStatus.text = if (isWithin) {
                "Lokasi Sesuai"
            } else {
                "Lokasi Belum Sesuai"
            }
        }

        viewModel.imageUri.observe(this) { uri ->
            uri?.let {
                Glide.with(this)
                    .load(it)
                    .centerCrop()
                    .into(binding.ivStore)
            }
        }

        viewModel.isVisitEnabled.observe(this) {
            binding.btnVisit.isEnabled = it
        }

        viewModel.visitResult.observe(this) {
            when {
                it is Resource.Success -> {
                    val store = viewModel.store.value
                    store?.let { value ->
                        val intent = Intent(this, StoreVisitActivity::class.java)
                        intent.putExtra(STORE_ID, value.id)

                        startActivity(intent).also { finish() }
                    }
                }

                it is Resource.Failure -> {
                    showShortToast(it.message)
                }
            }
        }
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
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

    private fun setUpButtonCamera() {
        binding.fabCamera.setOnClickListener {
            val imageFile = createImageFile()
            imageUri = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.provider",
                imageFile
            )

            takePicture.launch(imageUri)
        }
    }

    private fun createImageFile(): File {
        val dir = File(filesDir, "store_images")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        return File(dir, "${System.currentTimeMillis()}.jpg")
    }

}