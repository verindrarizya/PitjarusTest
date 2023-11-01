package com.verindrarizya.pitjarustest.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.verindrarizya.pitjarustest.databinding.ActivityMainBinding
import com.verindrarizya.pitjarustest.presentation.storelist.StoreListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpButtonClickListener()
    }

    private fun setUpButtonClickListener() {
        binding.fabKunjungan.setOnClickListener {
            val intent = Intent(this, StoreListActivity::class.java)
            startActivity(intent)
        }
    }
}