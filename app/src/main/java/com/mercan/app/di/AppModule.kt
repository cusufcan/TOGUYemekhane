package com.mercan.app.di

import android.content.Context
import androidx.room.Room
import com.mercan.app.data.local.MenuDatabase
import com.mercan.app.data.repository.PermissionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideToguMenuDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MenuDatabase::class.java, "togu_menu_database",
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideToguMenuDao(menuDatabase: MenuDatabase) = menuDatabase.getDao()

    @Singleton
    @Provides
    fun providePermissionRepository(@ApplicationContext context: Context) =
        PermissionRepository(context)
}