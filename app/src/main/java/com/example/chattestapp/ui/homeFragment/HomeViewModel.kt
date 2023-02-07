package com.example.chattestapp.ui.homeFragment

import androidx.lifecycle.ViewModel
import com.example.api.interfaces.Repository
import com.example.api.models.ChatItemView
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getChatsList(): List<ChatItemView>{
        return repository.getChatsList()
    }
}