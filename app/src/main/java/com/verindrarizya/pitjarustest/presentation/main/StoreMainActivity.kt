package com.verindrarizya.pitjarustest.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.verindrarizya.pitjarustest.databinding.ActivityStoreMainBinding
import com.verindrarizya.pitjarustest.presentation.login.LoginActivity
import com.verindrarizya.pitjarustest.presentation.storelist.StoreListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreMainActivity : AppCompatActivity() {

    private val binding: ActivityStoreMainBinding by lazy {
        ActivityStoreMainBinding.inflate(layoutInflater)
    }

    private val viewModel: StoreMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpObserver()
        setUpButtonClickListener()
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.username.collect { value ->
                    if (value == null) {
                        val intent =
                            Intent(this@StoreMainActivity, LoginActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        startActivity(intent)
                    } else {
                        binding.progressBar.isVisible = false
                        binding.groupContent.isVisible = true
                        binding.tvUsername.text = value
                    }
                }
            }
        }
    }

    private fun setUpButtonClickListener() {
        binding.fabKunjungan.setOnClickListener {
            val intent = Intent(this, StoreListActivity::class.java)
            startActivity(intent)
        }

        binding.fabLogout.setOnClickListener { viewModel.logout() }
    }
}