package ru.shcherbakov.weatherapp.presentation

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.shcherbakov.weatherapp.R
import ru.shcherbakov.weatherapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.myLooper()!!).postDelayed({
        binding.bottomNavMenu.setupWithNavController(
        navController = binding.navHostFragment.findNavController()
        )}, 1000)
        binding.bottomNavMenu.apply {
            // Отключаем все системные эффекты
            itemIconTintList = null
            itemRippleColor = null
            itemBackgroundResource = 0
        }
    }
}