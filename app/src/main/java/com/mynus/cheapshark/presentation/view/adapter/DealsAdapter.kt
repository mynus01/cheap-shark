package com.mynus.cheapshark.presentation.view.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mynus.cheapshark.R
import com.mynus.cheapshark.databinding.ItemDealBinding
import com.mynus.cheapshark.domain.model.Deal
import com.mynus.cheapshark.presentation.view.util.loadImage

class DealsAdapter(
    private val onItemClick: (deal: Deal) -> Unit
) : PagingDataAdapter<Deal, DealsAdapter.DealViewHolder>(DealDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        val binding = ItemDealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
        getItem(position)?.also { item -> holder.bind(item) }
    }

    inner class DealViewHolder(
        private val binding: ItemDealBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val currentPosition = absoluteAdapterPosition
                if (currentPosition != RecyclerView.NO_POSITION) {
                    getItem(currentPosition)?.also { item -> onItemClick(item) }
                }
            }
        }

        fun bind(deal: Deal) {
            binding.apply {
                tvTitle.text = deal.title
                ivThumb.loadImage(deal.thumb)
                tvNormalPrice.text = itemView.context.getString(R.string.deal_price_template, deal.normalPrice)
                if (deal.isOnSale && deal.salePrice.isNotBlank()) {
                    tvSalePrice.text = itemView.context.getString(R.string.deal_price_template, deal.salePrice)
                    tvSalePrice.isGone = false
                    tvNormalPrice.paintFlags = tvNormalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    tvSalePrice.isGone = true
                    tvNormalPrice.paintFlags = tvNormalPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    tvNormalPrice.paintFlags = tvNormalPrice.paintFlags
                }
            }
        }
    }
}