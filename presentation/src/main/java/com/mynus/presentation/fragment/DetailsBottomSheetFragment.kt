package com.mynus.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mynus.domain.model.Deal
import com.mynus.presentation.databinding.FragmentDetailsBinding
import com.mynus.presentation.util.loadImage

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