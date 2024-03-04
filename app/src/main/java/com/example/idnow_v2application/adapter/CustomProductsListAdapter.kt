package com.example.idnow_v2application.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.idnow_v2application.R
import com.example.idnow_v2application.product.Product

class CustomProductsListAdapter(
    private val context: Activity,
    private val list: List<Product>
) : ArrayAdapter<Product>(context, R.layout.custom_products_list, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.custom_products_list,null,true)

        val r_title = rootView.findViewById<TextView>(R.id.Title)
        val r_Price = rootView.findViewById<TextView>(R.id.Price)
        val r_Rating = rootView.findViewById<TextView>(R.id.Rating)

        val r_Image = rootView.findViewById<ImageView>(R.id.img)

        val products = list.get(position)
        r_title.text = products.title
        r_Price.text = "Price: " + products.price.toString()
        r_Rating.text = "Rating: 5/" + products.rating.toString()
        Glide.with(context).load(products.thumbnail).into(r_Image)

        return rootView


    }

}