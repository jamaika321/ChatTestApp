package com.example.chattestapp.di.modules

import android.content.Context
import com.example.api.interactors.CheckAuthCode
import com.example.api.interfaces.Repository
import com.example.core_db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(private val context: Context) {

    @Provides
    fun provideAppContext(): Context = context

}