package com.example.chattestapp.di.modules

import android.content.Context
import com.example.api.interfaces.Repository
import dagger.Module
import dagger.Provides

@Module
class DomainModule(private val context: Context) {

    @Provides
    fun provideAppContext(): Context = context


}