package com.mynus.cheapshark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.mynus.cheapshark.databinding.ActivityMainBinding
import com.mynus.domain.model.Deal
import com.mynus.presentation.adapter.DealsAdapter
import com.mynus.presentation.fragment.DetailsBottomSheetFragment
import com.mynus.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val adapter: DealsAdapter by lazy { DealsAdapter(::onItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateAndSetContentView()
        setToolbarIcon()

        setAdapter()
        observeDeals()
    }

    private fun inflateAndSetContentView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun setToolbarIcon() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher_foreground)
    }

    private fun setAdapter() {
        binding.rvDeals.adapter = adapter
    }

    private fun observeDeals() {
        lifecycleScope.launch {
            viewModel.state.collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun onItemClick(deal: Deal) {
        DetailsBottomSheetFragment(deal).show(supportFragmentManager, null)
    }
}