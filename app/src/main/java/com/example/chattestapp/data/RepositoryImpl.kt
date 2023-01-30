package com.example.chattestapp.data

import com.example.api.interfaces.Repository
import com.example.api.models.AuthUserRequestBody
import com.example.api.models.AuthUserResponseBody
import com.example.core_network.ApiService
import com.example.core_network.RetrofitBuilder
import retrofit2.Call
import javax.inject.Inject


class RepositoryImpl @Inject constructor() : Repository {
    override fun authorizationWithPhone(phoneNumber: String): Call<AuthUserResponseBody> {
        val phone = AuthUserRequestBody(phoneNumber)
        return RetrofitBuilder.makeService<ApiService>(true)
            .authUser(phone)
    }

}


