package com.example.chattestapp.ui.profileFragment

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.ErrorResponseBody
import com.example.api.models.PutUserInfoRequestBody
import com.example.api.models.PutUserInfoResponseBody
import com.example.entities.Either
import retrofit2.Call
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    suspend fun putUserInfo(userInfo: PutUserInfoRequestBody): Call<Either<ErrorResponseBody, PutUserInfoResponseBody>>{
        return repository.putUserInfo(userInfo)
    }
}