package com.mercan.app.ui.adapter.menulist

import androidx.recyclerview.widget.RecyclerView
import com.mercan.app.R
import com.mercan.app.databinding.ListItemBinding
import com.mercan.app.util.Icon

class MenuListViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(position: Int, mealName: String, isActive: Boolean) {
        binding.tvName.text = mealName
        binding.imgIcon.setImageResource(
            when (position) {
                0 -> Icon.SOUP.icon
                1 -> Icon.MEAL_FIRST.icon
                2 -> Icon.MEAL_SECOND.icon
                3 -> Icon.SWEET.icon
                4 -> Icon.TOTAL_CALORIES.icon
                else -> Icon.MEAL_FIRST.icon
            }
        )

        if (isActive) {
            val context = binding.root.context
            binding.cardView.strokeColor = context.getColor(R.color.list_tile_active)
        }
    }
}