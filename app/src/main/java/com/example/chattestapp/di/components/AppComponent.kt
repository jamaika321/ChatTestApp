package com.example.chattestapp.di.components

import com.example.chattestapp.MainActivity
import com.example.chattestapp.di.modules.DataModule
import com.example.chattestapp.di.modules.DomainModule
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.example.chattestapp.ui.loginFragment.LoginFragment
import com.example.chattestapp.ui.registerFragment.RegistrationFragment
import com.example.chattestapp.ui.verifyFragment.VerifyFragment
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
    fun inject(verifyFragment: VerifyFragment)
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(homeFragment: HomeFragment)
}