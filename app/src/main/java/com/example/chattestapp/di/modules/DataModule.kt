package com.example.chattestapp.di.modules

import android.content.Context
import com.example.api.interfaces.Repository
import com.example.chattestapp.data.RepositoryImpl
import com.example.core_db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRoomManager(context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    fun provideRepository(db: AppDatabase): Repository = RepositoryImpl(db)

}