package com.example.api.interactors

import com.example.api.interfaces.Repository
import com.example.api.models.CheckAuthCodeResponseBody
import com.example.entities.AsyncUseCase
import com.example.entities.Either
import com.example.entities.None
import retrofit2.Call

class CheckAuthCode(private val repository: Repository): AsyncUseCase<Call<CheckAuthCodeResponseBody>, CheckAuthCode.Params>() {
    data class Params(val code: String)

    override suspend fun run(params: Params): Call<CheckAuthCodeResponseBody> = repository.checkAuthCode(params.code)
}