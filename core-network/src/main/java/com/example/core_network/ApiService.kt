package com.example.core_network

import com.example.api.models.*
import com.example.entities.Either
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("send-auth-code/")
    fun authUser(@Body phoneNumber: AuthUserRequestBody): Call<Either<ErrorResponseBody, AuthUserResponseBody>>

    @POST("check-auth-code/")
    fun checkAuthCode(@Body authCode: CheckAuthCodeRequestBody): Call<Either<ErrorResponseBody, CheckAuthCodeResponseBody>>

    @POST("register/")
    fun register(@Body phoneNumber: RegisterRequestBody): Call<Either<ErrorResponseBody, RegisterResponseBody>>

    @GET("me/")
    fun getUserInfo(): Call<UserInfo>

    @PUT("me/")
    fun putUserInfo(@Body userInfo: PutUserInfoRequestBody): Call<Either<ErrorResponseBody, PutUserInfoResponseBody>>

    @POST("refresh-token/")
    fun refreshToken(@Body refreshTokenRequestBody: RefreshTokenRequestBody): Call<Either<ErrorResponseBody, RefreshTokenResponseBody>>

}