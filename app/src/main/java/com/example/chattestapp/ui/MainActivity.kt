package com.example.chattestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.api.models.CheckAuthCodeResponseBody
import com.example.chattestapp.App
import com.example.chattestapp.databinding.ActivityMainBinding
import com.example.chattestapp.ui.homeFragment.HomeFragment
import com.example.chattestapp.ui.loginFragment.LoginFragment
import com.example.chattestapp.utils.APP_ACTIVITY
import com.example.chattestapp.utils.replaceFragment
import kotlinx.coroutines.*
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

        checkRefreshToken()
    }

    private fun checkRefreshToken(){
        CoroutineScope(Dispatchers.Main + Job()).launch {
            val result = getRefreshToken()
            Log.i(TAG, "checkRefreshToken: ")
//            if (result.refreshToken.) {
//                replaceFragment(LoginFragment(), false)
//            } else {
//                replaceFragment(HomeFragment(), false)
//            }
        }
    }

    private suspend fun getRefreshToken(): CheckAuthCodeResponseBody = withContext(Dispatchers.IO) {
        viewModel.getRefreshToken()
    }

}