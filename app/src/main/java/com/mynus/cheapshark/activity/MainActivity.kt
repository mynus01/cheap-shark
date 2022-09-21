package com.mynus.cheapshark.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mynus.cheapshark.R
import com.mynus.cheapshark.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateAndSetContentView()
        setToolbarIcon()
    }

    private fun inflateAndSetContentView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setToolbarIcon() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher_foreground)
    }
}