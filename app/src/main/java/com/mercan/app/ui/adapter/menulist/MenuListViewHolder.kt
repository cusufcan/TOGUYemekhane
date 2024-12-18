package com.mercan.app.ui.adapter.menulist

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.mercan.app.databinding.ListItemBinding

class MenuListViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(mealName: String, isActive: Boolean) {
        binding.tvName.text = mealName

        if (isActive) {
            binding.cardView.strokeColor = Color.parseColor("#6FC272")
        }
    }
}