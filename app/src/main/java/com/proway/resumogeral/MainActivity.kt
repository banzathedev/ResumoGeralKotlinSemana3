package com.proway.resumogeral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.proway.resumogeral.adapter.AdapterRecyclerViewProductsList
import com.proway.resumogeral.model.ClickableItem
import com.proway.resumogeral.model.Products

import com.proway.resumogeral.service.RetrofitBuilder
import com.proway.resumogeral.utis.snackBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<Products>>, ClickableItem{
    private var products = listOf<Products>()
    private val adapter = AdapterRecyclerViewProductsList(this)
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        triggerProductsRequest()
        loadComponents()

    }

    private fun loadComponents() {
        recyclerView = findViewById(R.id.RecyclerViewId)
        recyclerView.layoutManager = GridLayoutManager(this,  2)
        recyclerView.adapter = adapter

    }

    private fun triggerProductsRequest() {
        val service = RetrofitBuilder.getProductsServices()
        val call = service.getProducts()
        call.clone().enqueue(this)
    }

    override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
        response.body()?.apply {
           products = this
            adapter.update(this)
        }
    }

    override fun onFailure(call: Call<List<Products>>, t: Throwable) {
        snackBar(window.decorView, R.string.invalid_request)
    }

    override fun onClickSelectItem(products: Products) {
            val intentToProdcutsDetail = Intent(this, ProductDetail_Activity::class.java)
            intentToProdcutsDetail.putExtra("ProductId", products.id)
            startActivity(intentToProdcutsDetail)

    }
}
