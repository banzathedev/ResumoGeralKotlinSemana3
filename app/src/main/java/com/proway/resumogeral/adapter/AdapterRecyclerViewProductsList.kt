package com.proway.resumogeral.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proway.resumogeral.R
import com.proway.resumogeral.model.ClickableItem
import com.proway.resumogeral.model.Products



class AdapterRecyclerViewProductsList(val takeDataOfSelectedProduct: ClickableItem) : RecyclerView.Adapter<ViewHolder>() {

        private var productsLista: List<Products> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


            productsLista[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                takeDataOfSelectedProduct.onClickSelectItem(this)
            }
        }

    }
    fun update(newProductsList: List<Products>){
        productsLista = mutableListOf()
        productsLista = newProductsList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productsLista.size
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name = view.findViewById<TextView>(R.id.productName)
    var price = view.findViewById<TextView>(R.id.productPrice)
    var category = view.findViewById<TextView>(R.id.productCategory)
    var image = view.findViewById<ImageView>(R.id.productImage)


    fun bind(products: Products) {
        name.text = products.title
        price.text = products.price.toString()
        category.text = products.category
        image.apply() {
            Glide.with(context)
                .load(products.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(this)
        }

    }

}
