package com.example.chattestapp.data

import com.example.api.interfaces.Repository
import com.example.api.models.*
import com.example.core_db.AppDatabase
import com.example.core_network.ApiService
import com.example.core_network.RetrofitBuilder
import retrofit2.Call
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val db: AppDatabase) : Repository {
    override suspend fun authorizationWithPhone(phoneNumber: String): Call<AuthUserResponseBody> {
        val phone = AuthUserRequestBody(phoneNumber)
        return RetrofitBuilder.makeService<ApiService>(true)
            .authUser(phone)
    }

    override suspend fun checkAuthCode(code: String): Call<CheckAuthCodeResponseBody> {
        val authCodeModel = CheckAuthCodeRequestBody("+79219999999", code)
        return RetrofitBuilder.makeService<ApiService>(true)
            .checkAuthCode(authCodeModel)
    }

    override suspend fun saveAccessToken(accessToken: CheckAuthCodeResponseBody) {
        db.userInfoDao().saveAccessToken(accessToken)
    }

    override suspend fun getUserToken(): CheckAuthCodeResponseBody {
        return db.userInfoDao().getUserToken()
    }

    override fun getChatsList(): List<ChatItemView> {
        return listOf(ChatItemView(1,"","Anton", "Hello", "24 Nov")
            ,ChatItemView(2,"","Oleg", "Playing", "02 Feb")
            ,ChatItemView(3,"","Aleksei", "My grandfather was a hero", "9 May")
            ,ChatItemView(4,"","Romanych", "I want to tell you about my skills.", "8 Nov")
            ,ChatItemView(5,"","Anton", "Hello", "24 Nov")
            ,ChatItemView(6,"","Oleg", "Playing", "02 Feb")
            ,ChatItemView(7,"","Aleksei", "My grandfather was a hero", "9 May")
            ,ChatItemView(8,"","Romanych", "I want to tell you about my skills.", "8 Nov"))
    }


}


