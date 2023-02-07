package com.example.api.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "access_token")
data class CheckAuthCodeResponseBody (
    @PrimaryKey
    @SerializedName("refresh_token")
    var refreshToken: String = "",
    @SerializedName("access_token")
    var accessToken: String = "",
    @SerializedName("user_id")
    var userId: Int,
    @SerializedName("is_user_exists")
    var isUserExists: Boolean = false
    )
