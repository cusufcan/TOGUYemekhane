package com.mercan.app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercan.app.data.model.MenuData

class MenuListViewModel : ViewModel() {
    val menuData = MutableLiveData<MenuData>()
}