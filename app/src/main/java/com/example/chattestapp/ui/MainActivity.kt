package com.example.chattestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.chattestapp.App
import com.example.chattestapp.databinding.ActivityMainBinding
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.example.chattestapp.ui.loginFragment.LoginFragment
import com.example.chattestapp.ui.registrationFragment.RegistrationFragment
import com.example.chattestapp.utils.APP_ACTIVITY
import com.example.chattestapp.utils.launchOn
import com.example.chattestapp.utils.replaceFragment
import com.example.chattestapp.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        (application as App).appComponent.inject(this)

        //TODO
        replaceFragment(RegistrationFragment(), false)
//        getRefreshToken()
//        initVars()
    }

    private fun initVars(){
        viewModel.token.launchOn(lifecycleScope) {
            if (it.refreshToken.isEmpty()){
                replaceFragment(LoginFragment(), false)
            } else {
                replaceFragment(HomeFragment(), false)
            }
        }
    }

    private fun getRefreshToken() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            viewModel.getRefreshToken()
        }
        job.start()
    }
}