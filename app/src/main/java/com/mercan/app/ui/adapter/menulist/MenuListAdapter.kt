package com.mercan.app.ui.adapter.menulist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.app.data.model.MenuList
import com.mercan.app.databinding.ListItemBinding

class MenuListAdapter(
    private val menuList: MenuList,
    private val isActive: Boolean,
) : RecyclerView.Adapter<MenuListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return MenuListViewHolder(binding)
    }

    override fun getItemCount() = menuList.menuList.size

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        holder.bind(position, menuList.menuList[position], isActive)
    }
}