package com.mynus.cheapshark.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
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

        setViewsBehaviour()
        initObservers()
    }

    private fun inflateAndSetContentView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun setToolbarIcon() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher_foreground)
    }

    private fun setViewsBehaviour() {
        binding.apply {
            rvDeals.adapter = adapter

            swDeals.setOnRefreshListener {
                viewModel.getDeals()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            viewModel.loadingState.collect { isLoading ->
                binding.apply {
                    swDeals.isRefreshing = isLoading
                    rvDeals.isGone = isLoading
                }
            }
        }

        viewModel.getDeals()
    }

    private fun onItemClick(deal: Deal) {
        DetailsBottomSheetFragment(deal).show(supportFragmentManager, null)
    }
}