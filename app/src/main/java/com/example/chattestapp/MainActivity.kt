package com.example.chattestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chattestapp.databinding.ActivityMainBinding
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.example.chattestapp.ui.loginFragment.LoginFragment
import com.example.chattestapp.utils.APP_ACTIVITY
import com.example.chattestapp.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        (application as App).appComponent.inject(this)

//        replaceFragment(LoginFragment(), false)
        replaceFragment(HomeFragment(), false)
    }
}