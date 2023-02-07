package com.example.chattestapp.ui

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.CheckAuthCodeResponseBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _token = MutableStateFlow(CheckAuthCodeResponseBody(userId = 0))
    val token = _token.asStateFlow()

    fun getRefreshToken() {
        repository.getUserToken().let {
            if (it != null){
                _token.value = repository.getUserToken()
            }
        }
    }
}