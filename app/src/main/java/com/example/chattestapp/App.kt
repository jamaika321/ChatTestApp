package com.example.chattestapp

import android.app.Application
import com.example.chattestapp.di.components.AppComponent
import com.example.chattestapp.di.components.DaggerAppComponent
import com.example.chattestapp.di.modules.DomainModule

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(DomainModule(this))
    }

    companion object {
        lateinit var user:String
    }
}