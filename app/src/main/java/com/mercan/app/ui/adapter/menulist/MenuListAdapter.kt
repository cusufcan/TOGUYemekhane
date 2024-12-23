package com.mercan.app.ui.adapter.menulist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.app.data.model.ToguMenuList
import com.mercan.app.databinding.ListItemBinding

class MenuListAdapter(
    private val toguMenuList: ToguMenuList,
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

    override fun getItemCount() = toguMenuList.menuList.size

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        holder.bind(position, toguMenuList.menuList[position], isActive)
    }
}