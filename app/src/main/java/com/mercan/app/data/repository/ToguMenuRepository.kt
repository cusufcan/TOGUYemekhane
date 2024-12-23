package com.mercan.app.data.repository

import com.mercan.app.data.local.ToguMenuLocalSource
import com.mercan.app.data.model.ToguMenuData
import com.mercan.app.data.remote.ToguMenuRemoteSource
import javax.inject.Inject

class ToguMenuRepository @Inject constructor(
    private val toguMenuRemoteSource: ToguMenuRemoteSource,
    private val toguMenuLocalSource: ToguMenuLocalSource,
) {
    suspend fun getData(): ToguMenuData {
        // İnternetten veya yerel veri kaynağından verileri çek
        return toguMenuRemoteSource.getData()
    }
}