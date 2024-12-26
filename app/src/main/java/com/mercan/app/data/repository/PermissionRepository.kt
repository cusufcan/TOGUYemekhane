package com.mercan.app.data.repository

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import javax.inject.Inject

class PermissionRepository @Inject constructor(
    val context: Context,
) {
    fun isNotificationPermissionGranted() =
        NotificationManagerCompat.from(context).areNotificationsEnabled()

    fun requestNotificationPermission(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1001
            )
        }
    }
}