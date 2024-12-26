package com.mercan.app.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.mercan.app.data.repository.PermissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val permissionRepository: PermissionRepository,
) : ViewModel() {
    private val _permissionState = MutableStateFlow(false)
    val permissionState get() = _permissionState

    fun checkPermission() {
        _permissionState.value = permissionRepository.isNotificationPermissionGranted()
    }

    fun requestPermission(activity: Activity) {
        permissionRepository.requestNotificationPermission(activity)
    }
}