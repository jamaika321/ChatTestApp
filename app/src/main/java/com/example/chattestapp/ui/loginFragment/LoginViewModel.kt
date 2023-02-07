package com.example.chattestapp.ui.loginFragment

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.AuthUserResponseBody
import com.example.api.models.ErrorResponseBody
import com.example.entities.Either
import retrofit2.Call
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun sendAuthCode(phoneNumber: String): Call<Either<ErrorResponseBody, AuthUserResponseBody>> {
        return repository.authorizationWithPhone(phoneNumber)
    }
}