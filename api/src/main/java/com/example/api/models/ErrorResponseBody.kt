package com.example.api.models

import com.google.gson.annotations.SerializedName

data class ErrorResponseBody(
    @SerializedName("detail")
    val detail: ArrayList<Detail>
)

data class Detail(
    @SerializedName("loc")
    var loc: ArrayList<String>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("type")
    var type: String
)