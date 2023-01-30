package com.example.chattestapp.ui

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.AuthUserResponseBody
import retrofit2.Call
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun sendAuthCode(phoneNumber: String): Call<AuthUserResponseBody> {
        return repository.authorizationWithPhone(phoneNumber)
    }
}