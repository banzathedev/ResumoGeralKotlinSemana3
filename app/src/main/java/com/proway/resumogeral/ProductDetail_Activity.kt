package com.proway.resumogeral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.proway.resumogeral.model.Products

class ProductDetail_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val clickableItemId = intent.getSerializableExtra("ProductId") as Products?
        loadComponents(clickableItemId)
    }

    private fun loadComponents(products: Products?) {
        products?.let { product ->
            supportActionBar?.title = "${product.title} "
            findViewById<ImageView>(R.id.productImage).apply {
                Glide.with(context)
                    .load(product.image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(this)
            }
            findViewById<TextView>(R.id.productName).apply {
                text = product.title
            }
            findViewById<TextView>(R.id.productPrice).apply {
                val price = product.price.toString()
                text = "Price is $: ${price}"
            }
            findViewById<TextView>(R.id.productCategory).apply {
                val ctg = product.category
                text = "Category : $ctg"
            }
            findViewById<TextView>(R.id.productDesc).apply {
                val desc = product.description
                text = "Aboute : $desc"
            }
        }
    }
}