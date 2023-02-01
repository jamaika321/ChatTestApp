package com.example.chattestapp.ui.verifyFragment

import androidx.lifecycle.ViewModel
import com.example.api.interactors.CheckAuthCode
import com.example.api.interfaces.Repository
import com.example.api.models.CheckAuthCodeResponseBody
import retrofit2.Call
import javax.inject.Inject

class VerifyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    suspend fun checkAuthCode(code: String): Call<CheckAuthCodeResponseBody> {
        return repository.checkAuthCode(code)
    }

    suspend fun saveUserToken(token: CheckAuthCodeResponseBody) {
        repository.saveAccessToken(token)
    }

}