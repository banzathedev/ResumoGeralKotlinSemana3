package com.proway.resumogeral.service

import com.proway.resumogeral.model.Products
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface ProductListService {
    @GET("/products")
    fun getProducts(): Call<List<Products>>
}