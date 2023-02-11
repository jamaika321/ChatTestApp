package com.example.chattestapp.data

import com.example.api.interfaces.Repository
import com.example.api.models.*
import com.example.core_db.AppDatabase
import com.example.core_network.ApiService
import com.example.core_network.RetrofitBuilder
import com.example.entities.Either
import retrofit2.Call
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val db: AppDatabase) : Repository {
    override suspend fun sendAuthCode(phoneNumber: AuthUserRequestBody): Call<Either<ErrorResponseBody, AuthUserResponseBody>> {
        return RetrofitBuilder.makeService<ApiService>(true)
            .sendAuthCode(phoneNumber)
    }

    override suspend fun checkAuthCode(code: CheckAuthCodeRequestBody): Call<Either<ErrorResponseBody, CheckAuthCodeResponseBody>> {
        return RetrofitBuilder.makeService<ApiService>(true)
            .checkAuthCode(code)
    }

    override suspend fun register(user: RegisterRequestBody): Call<Either<ErrorResponseBody, RegisterResponseBody>> {
        return RetrofitBuilder.makeService<ApiService>(true)
            .register(user)
    }

    override suspend fun saveAccessToken(accessToken: CheckAuthCodeResponseBody) {
        db.userInfoDao().saveAccessToken(accessToken)
    }

    override fun getUserToken(): CheckAuthCodeResponseBody {
        return db.userInfoDao().getUserToken()
    }

    override suspend fun getChatsList(): List<ChatItemView> {
        return listOf(ChatItemView(1,"","Anton", "Hello", "24 Nov")
            ,ChatItemView(2,"","Oleg", "Playing", "02 Feb")
            ,ChatItemView(3,"","Aleksei", "My grandfather was a hero", "9 May")
            ,ChatItemView(4,"","Romanych", "I want to tell you about my skills.", "8 Nov")
            ,ChatItemView(5,"","Anton", "Hello", "24 Nov")
            ,ChatItemView(6,"","Oleg", "Playing", "02 Feb")
            ,ChatItemView(7,"","Aleksei", "My grandfather was a hero", "9 May")
            ,ChatItemView(8,"","Romanych", "I want to tell you about my skills.", "8 Nov"))
    }

    override suspend fun getUserInfo(): Call<UserInfo> {
        return RetrofitBuilder.makeService<ApiService>(true).getUserInfo()
    }

    override suspend fun putUserInfo(userInfo: PutUserInfoRequestBody): Call<Either<ErrorResponseBody, PutUserInfoResponseBody>> {
        return RetrofitBuilder.makeService<ApiService>(true).putUserInfo(userInfo)
    }

    override suspend fun refreshToken(refreshToken: RefreshTokenRequestBody): Call<Either<ErrorResponseBody, RefreshTokenResponseBody>> {
        return RetrofitBuilder.makeService<ApiService>(true).refreshToken(refreshToken)
    }


}


