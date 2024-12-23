package com.mercan.app.di

import android.content.Context
import androidx.room.Room
import com.mercan.app.data.local.ToguMenuDatabase
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
        context, ToguMenuDatabase::class.java, "togu_menu_database",
    ).build()

    @Singleton
    @Provides
    fun provideToguMenuDao(toguMenuDatabase: ToguMenuDatabase) = toguMenuDatabase.getDao()
}