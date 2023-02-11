package com.example.api.interfaces

import com.example.api.models.*
import com.example.entities.Either
import retrofit2.Call

interface Repository {
    suspend fun sendAuthCode(phoneNumber: AuthUserRequestBody): Call<Either<ErrorResponseBody, AuthUserResponseBody>>

    suspend fun checkAuthCode(code: CheckAuthCodeRequestBody): Call<Either<ErrorResponseBody, CheckAuthCodeResponseBody>>

    suspend fun register(user: RegisterRequestBody): Call<Either<ErrorResponseBody, RegisterResponseBody>>

    suspend fun saveAccessToken(accessToken: CheckAuthCodeResponseBody)

    fun getUserToken(): CheckAuthCodeResponseBody

    suspend fun getChatsList(): List<ChatItemView>

    suspend fun getUserInfo(): Call<UserInfo>

    suspend fun putUserInfo(userInfo: PutUserInfoRequestBody): Call<Either<ErrorResponseBody, PutUserInfoResponseBody>>

    suspend fun refreshToken(refreshToken: RefreshTokenRequestBody): Call<Either<ErrorResponseBody, RefreshTokenResponseBody>>
}