package com.sahil.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.sahil.demo.databinding.ActivityMainBinding
import com.sahil.demo.views.home.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        navController = findNavController(R.id.fragmentView)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentView, Home()) // Replace with your initial fragment
                .commitNow()
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> navController.navigate(R.id.home2)
                R.id.menu_settings -> navController.navigate(R.id.setting)
                R.id.menu_notifications -> navController.navigate(R.id.notification)
                R.id.menu_profile ->navController.navigate(R.id.profile)
            }
            true
        }
    }

}


//AIzaSyCMffT3rK-OK0RR9T1rVQfUg21BNmVYR9s