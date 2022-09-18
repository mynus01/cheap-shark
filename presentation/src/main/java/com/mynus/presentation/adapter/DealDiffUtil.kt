package com.mynus.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mynus.domain.model.Deal

class DealDiffUtil: DiffUtil.ItemCallback<Deal>() {
    override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem.dealID == newItem.dealID
    }

    override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem == newItem
    }
}