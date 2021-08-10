package com.proway.resumogeral.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofitFake = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAuthenticationServices(): Authentication {
        return retrofitFake.create(Authentication::class.java)

    }
    fun getProductsServices(): ProductListService {
        return retrofitFake.create(ProductListService::class.java)
    }
}