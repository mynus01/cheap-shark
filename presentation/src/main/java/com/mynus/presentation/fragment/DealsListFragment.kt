package com.mynus.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mynus.domain.model.Deal
import com.mynus.presentation.adapter.DealsAdapter
import com.mynus.presentation.databinding.FragmentDealsListBinding
import com.mynus.presentation.parcelable.mapper.DealParcelableMapper
import com.mynus.presentation.viewmodel.DealsListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DealsListFragment: Fragment() {
    private lateinit var binding: FragmentDealsListBinding
    private val viewModel: DealsListViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val adapter: DealsAdapter by lazy { DealsAdapter(::onItemClick) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDealsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewsBehaviour()
        initObservers()
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
            viewModel.loadingState.collect { isLoading ->
                binding.apply {
                    swDeals.isRefreshing = isLoading
                    rvDeals.isGone = isLoading
                }
            }
        }

        lifecycleScope.launch {
            viewModel.state.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        viewModel.getDeals()
    }

    private fun onItemClick(deal: Deal) {
        navController.navigate(
            DealsListFragmentDirections.actionDealsListFragmentToDetailsBottomSheetFragment(
                DealParcelableMapper.toParcelable(deal)
            )
        )
    }
}