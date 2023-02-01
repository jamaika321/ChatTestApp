package com.example.api.interfaces

import com.example.api.models.AuthUserResponseBody
import com.example.api.models.CheckAuthCodeResponseBody
import retrofit2.Call

interface Repository {
    suspend fun authorizationWithPhone(phoneNumber: String): Call<AuthUserResponseBody>
    suspend fun checkAuthCode(code: String): Call<CheckAuthCodeResponseBody>

    suspend fun saveAccessToken(accessToken: CheckAuthCodeResponseBody)
    suspend fun getUserToken(): CheckAuthCodeResponseBody
}