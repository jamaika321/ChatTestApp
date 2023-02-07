package com.example.api.models

data class PutUserInfoResponseBody(
    val avatars: Avatars
)


data class Avatars(
    val avatar: String,
    val bigAvatar: String,
    val miniAvatar: String
)
