package com.example.chattestapp.ui

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.AuthUserResponseBody
import com.example.api.models.CheckAuthCodeResponseBody
import javax.inject.Inject

class ActivityViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun getRefreshToken(): CheckAuthCodeResponseBody{
        return repository.getUserToken()
    }
}