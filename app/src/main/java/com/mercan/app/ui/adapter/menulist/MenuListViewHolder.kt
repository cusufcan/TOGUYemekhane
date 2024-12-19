package com.mercan.app.ui.adapter.menulist

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.mercan.app.databinding.ListItemBinding
import com.mercan.app.util.Icons

class MenuListViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(position: Int, mealName: String, isActive: Boolean) {
        binding.tvName.text = mealName
        binding.imgIcon.setImageResource(
            when (position) {
                0 -> Icons.SOUP.icon
                1 -> Icons.MEAL_FIRST.icon
                2 -> Icons.MEAL_SECOND.icon
                3 -> Icons.SWEET.icon
                4 -> Icons.TOTAL_CALORIES.icon
                else -> Icons.MEAL_FIRST.icon
            }
        )

        if (isActive) {
            binding.cardView.strokeColor = Color.parseColor("#6FC272")
        }
    }
}