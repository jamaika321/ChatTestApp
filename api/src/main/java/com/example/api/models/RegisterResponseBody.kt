package com.example.api.models

import com.google.gson.annotations.SerializedName

data class RegisterResponseBody(
    @SerializedName("refresh_token")
    var refreshToken: String,
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("user_id")
    var userId: Int
) {
}