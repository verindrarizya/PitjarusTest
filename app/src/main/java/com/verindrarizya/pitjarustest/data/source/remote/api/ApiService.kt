package com.verindrarizya.pitjarustest.data.source.remote.api

import com.verindrarizya.pitjarustest.data.source.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("api/sariroti_md/index.php/login/loginTest")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

}