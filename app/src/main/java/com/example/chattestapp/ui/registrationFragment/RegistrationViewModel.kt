package com.example.chattestapp.ui.registrationFragment

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.ErrorResponseBody
import com.example.api.models.RegisterRequestBody
import com.example.api.models.RegisterResponseBody
import com.example.entities.Either
import retrofit2.Call
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    suspend fun register(user: RegisterRequestBody): Call<Either<ErrorResponseBody, RegisterResponseBody>> {
        return repository.register(user)
    }
}