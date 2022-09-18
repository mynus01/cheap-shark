package com.mynus.cheapshark.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mynus.cheapshark.databinding.FragmentDetailsBinding
import com.mynus.cheapshark.domain.model.Deal
import com.mynus.cheapshark.presentation.view.util.loadImage

class DetailsBottomSheetFragment(
    private val deal: Deal
) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvTitle.text = deal.title
            ivThumb.loadImage(deal.thumb)
        }
    }
}