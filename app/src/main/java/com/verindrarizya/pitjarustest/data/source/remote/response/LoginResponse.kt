package com.verindrarizya.pitjarustest.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("stores")
    val stores: List<StoreResponse>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)


