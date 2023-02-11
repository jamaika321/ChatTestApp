package com.example.chattestapp.ui.homeFragment

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.ChatItemView
import java.io.IOException
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    suspend fun getChatsList(): List<ChatItemView>{
        throw IOException()
        return repository.getChatsList()
    }
}