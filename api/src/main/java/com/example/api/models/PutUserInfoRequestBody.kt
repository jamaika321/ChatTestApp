package com.example.api.models

import com.google.gson.annotations.SerializedName

data class PutUserInfoRequestBody(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("birthday")
    var birthday: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("vk")
    var vk: String? = null,
    @SerializedName("instagram")
    var instagram: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("avatar")
    var avatar: Avatar? = Avatar()

)

data class Avatar(

    @SerializedName("filename")
    var filename: String? = null,
    @SerializedName("base_64")
    var base64: String? = null

)