package com.mercan.app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercan.app.data.model.ToguMenuData

class MenuListViewModel : ViewModel() {
    val menuData = MutableLiveData<ToguMenuData>()
}