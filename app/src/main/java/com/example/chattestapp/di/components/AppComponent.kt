package com.example.chattestapp.di.components

import com.example.chattestapp.MainActivity
import com.example.chattestapp.di.modules.DataModule
import com.example.chattestapp.di.modules.DomainModule
import com.example.chattestapp.ui.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, DataModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(domainModule: DomainModule): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(loginFragment: LoginFragment)
}