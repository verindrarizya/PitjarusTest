package com.verindrarizya.pitjarustest.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.verindrarizya.pitjarustest.databinding.ActivityLoginBinding
import com.verindrarizya.pitjarustest.presentation.main.StoreMainActivity
import com.verindrarizya.pitjarustest.util.Resource
import com.verindrarizya.pitjarustest.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpObserver()
        setButtonLoginClickListener()
    }

    private fun setUpObserver() {
        viewModel.loginResource.observe(this) { value ->
            binding.progressBar.isVisible = value is Resource.Loading
            binding.btnLogin.isVisible = value !is Resource.Loading

            if (value is Resource.Success) {
                showShortToast(value.data)

                val intent = Intent(this, StoreMainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
            } else if (value is Resource.Failure) {
                showShortToast(value.message)
            }
        }
    }

    private fun setButtonLoginClickListener() {
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            if (username.isEmpty() && password.isEmpty()) {
                showShortToast("Username and Password cannot be empty")
            } else if (password.isEmpty()) {
                showShortToast("Password cannot be empty")
            } else if (username.isEmpty()) {
                showShortToast("Username cannot be empty")
            } else {
                viewModel.login(username, password)
            }
        }
    }
}