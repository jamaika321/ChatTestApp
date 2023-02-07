package com.example.api.models

import com.google.gson.annotations.SerializedName

data class CheckAuthCodeRequestBody(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("code")
    val code: String
) {
}