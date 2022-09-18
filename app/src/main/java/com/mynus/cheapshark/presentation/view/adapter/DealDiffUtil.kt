package com.mynus.cheapshark.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mynus.cheapshark.domain.model.Deal

class DealDiffUtil: DiffUtil.ItemCallback<Deal>() {
    override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem.dealID == newItem.dealID
    }

    override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem == newItem
    }
}