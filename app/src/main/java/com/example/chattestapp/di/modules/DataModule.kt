package com.example.chattestapp.di.modules

import com.example.api.interfaces.Repository
import com.example.chattestapp.data.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideRepository(): Repository = RepositoryImpl()

}