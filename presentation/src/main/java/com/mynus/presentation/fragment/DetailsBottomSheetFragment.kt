package com.mynus.presentation.fragment

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mynus.domain.model.Deal
import com.mynus.presentation.R
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
            tvNormalPrice.text = root.context.getString(R.string.deal_price_template, deal.normalPrice)
            if (deal.isOnSale && deal.salePrice.isNotBlank()) {
                tvSalePrice.text = root.context.getString(R.string.deal_price_template, deal.salePrice)
                tvSalePrice.isInvisible = false
                tvNormalPrice.paintFlags = tvNormalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                tvSalePrice.isInvisible = true
                tvNormalPrice.paintFlags = tvNormalPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                tvNormalPrice.paintFlags = tvNormalPrice.paintFlags
            }
        }
    }
}