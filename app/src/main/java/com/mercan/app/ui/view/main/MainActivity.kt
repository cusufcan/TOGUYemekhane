package com.mercan.app.ui.view.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mercan.app.databinding.ActivityMainBinding
import com.mercan.app.ui.viewmodel.PermissionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavigationView: BottomNavigationView

    private val permissionViewModel: PermissionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivityCodes()
        defaultActivityCodes()
        bindViews()
        requestPermission()
    }

    private fun defaultActivityCodes() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun bindingActivityCodes() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bindViews() {
        navHostFragment = supportFragmentManager.findFragmentById(
            binding.fragmentContainerView.id
        ) as NavHostFragment

        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }

    private fun requestPermission() {
        lifecycleScope.launch {
            permissionViewModel.permissionState.collect {
                if (!it) permissionViewModel.requestPermission(this@MainActivity)
            }
        }

        permissionViewModel.checkPermission()
    }
}