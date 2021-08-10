package com.proway.resumogeral.service

import com.proway.resumogeral.model.Credential
import com.proway.resumogeral.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Authentication {
    @POST("/auth/login")
    fun login(@Body credential: Credential): Call<LoginResponse>
}