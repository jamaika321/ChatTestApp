package com.example.api.models

import com.google.gson.annotations.SerializedName

data class RegisterRequestBody(
    @SerializedName("phone")
    var phone: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("username")
    var username: String
) {
}