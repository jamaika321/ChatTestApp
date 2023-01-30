package com.example.api.interfaces

import com.example.api.models.AuthUserResponseBody
import retrofit2.Call

interface Repository {
    fun authorizationWithPhone(phoneNumber: String): Call<AuthUserResponseBody>
}