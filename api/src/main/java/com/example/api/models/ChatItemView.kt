package com.example.api.models

data class ChatItemView(
    val id: Int,
    val image: String,
    val name: String,
    val lastMessage: String,
    val lastMessageDate: String
)