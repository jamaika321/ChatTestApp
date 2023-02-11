package com.example.chattestapp.ui.loginFragment

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: Repository) : ViewModel() {
//    suspend fun sendAuthCode(phoneNumber: AuthUserRequestBody): Either<ErrorResponseBody, AuthUserResponseBody> {
//        return repository.sendAuthCode(phoneNumber)
//    }
}