package com.example.core_network

import com.example.api.models.*
import com.example.entities.Either
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("send-auth-code/")
    suspend fun sendAuthCode(@Body phoneNumber: AuthUserRequestBody): Call<Either<ErrorResponseBody, AuthUserResponseBody>>

    @POST("check-auth-code/")
    suspend fun checkAuthCode(@Body authCode: CheckAuthCodeRequestBody): Call<Either<ErrorResponseBody, CheckAuthCodeResponseBody>>

    @POST("register/")
    suspend fun register(@Body phoneNumber: RegisterRequestBody): Call<Either<ErrorResponseBody, RegisterResponseBody>>

    @GET("me/")
    suspend fun getUserInfo(): Call<UserInfo>

    @PUT("me/")
    suspend fun putUserInfo(@Body userInfo: PutUserInfoRequestBody): Call<Either<ErrorResponseBody, PutUserInfoResponseBody>>

    @POST("refresh-token/")
    suspend fun refreshToken(@Body refreshTokenRequestBody: RefreshTokenRequestBody): Call<Either<ErrorResponseBody, RefreshTokenResponseBody>>

}