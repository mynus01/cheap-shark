package com.mynus.presentation.fragment

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mynus.core.util.Constants
import com.mynus.domain.model.Deal
import com.mynus.presentation.R
import com.mynus.presentation.databinding.FragmentDetailsBottomSheetBinding
import com.mynus.presentation.parcelable.mapper.DealParcelableMapper
import com.mynus.presentation.util.loadImage
import com.mynus.presentation.util.openUrl

class DetailsBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDetailsBottomSheetBinding
    private val args: DetailsBottomSheetFragmentArgs by navArgs()
    private val deal: Deal by lazy { DealParcelableMapper.fromParcelable(args.deal) }

    override fun onStart() {
        super.onStart()

        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvTitle.text = deal.title
            tvNormalPrice.text = root.context.getString(R.string.deal_price_template, deal.normalPrice)
            ivThumb.loadImage(deal.thumb)

            if (deal.isOnSale && deal.salePrice.isNotBlank()) {
                tvSalePrice.text = root.context.getString(R.string.deal_price_template, deal.salePrice)
                tvSalePrice.isGone = false

                tvNormalPrice.paintFlags = tvNormalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                tvNormalPrice.setTextColor(ResourcesCompat.getColor(root.resources, R.color.red_orange, null))

                tvDiscountPercentage.text = String.format("-%.0f%%", 100 - (deal.salePrice.toDouble() / deal.normalPrice.toDouble() * 100))
                cvDiscountPercentage.isGone = false
            } else {
                tvSalePrice.isGone = true
                cvDiscountPercentage.isGone = true

                tvNormalPrice.paintFlags = tvNormalPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                tvNormalPrice.paintFlags = tvNormalPrice.paintFlags

                tvNormalPrice.setTextColor(ResourcesCompat.getColor(root.resources, android.R.color.white, null))
            }
            setOpenUrlOnClickBehaviour(cvSteam, Constants.Urls.STEAM_APP, deal.steamAppID)
            setOpenUrlOnClickBehaviour(cvMetacritic, Constants.Urls.METACRITIC, deal.metacriticLink)
        }
    }

    private fun setOpenUrlOnClickBehaviour(view: View, baseUrl: String, endpoint: String?) {
        if (endpoint != null) {
            view.isInvisible = false

            view.setOnClickListener {
                view.context.openUrl(baseUrl + endpoint)
            }
        } else {
            view.isInvisible = true
        }
    }
}
