package com.mynus.cheapshark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mynus.cheapshark.databinding.ActivityMainBinding
import com.mynus.datasource.mediator.DealsMediator
import com.mynus.domain.model.Deal
import com.mynus.presentation.adapter.DealsAdapter
import com.mynus.presentation.fragment.DetailsBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var mediator: DealsMediator
    private val adapter: DealsAdapter by lazy { DealsAdapter(::onItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateAndSetContentView()

        setAdapter()
        observeDeals()
    }

    private fun inflateAndSetContentView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setAdapter() {
        binding.rvDeals.adapter = adapter
    }

    private fun observeDeals() {
        lifecycleScope.launch {
            mediator.getDeals().flowWithLifecycle(
                lifecycle,
                Lifecycle.State.STARTED
            ).collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun onItemClick(deal: Deal) {
        DetailsBottomSheetFragment(deal).show(supportFragmentManager, null)
    }
}