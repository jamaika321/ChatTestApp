package com.example.core_network

import com.example.api.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("send-auth-code/")
    fun authUser(@Body phoneNumber: AuthUserRequestBody): Call<AuthUserResponseBody>

    @POST("check-auth-code/")
    fun checkAuthCode(@Body authCode: CheckAuthCodeRequestBody): Call<CheckAuthCodeResponseBody>

    @POST("register/")
    fun register(@Body phoneNumber: RegisterRequestBody): Call<RegisterResponseBody>

//    @GET("/me/")
//    fun getUserInfo(): Call

}