package com.example.idnow_v2application

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.idnow_v2application.adapter.CustomProductsListAdapter
import com.example.idnow_v2application.di.NetworkModule
import com.example.idnow_v2application.di.NetworkModule.createGsonConverterFactory
import com.example.idnow_v2application.di.NetworkModule.createOkHttpClient
import com.example.idnow_v2application.network.ProductApi
import com.example.idnow_v2application.product.ProductsResponse
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {
    lateinit var productApi : ProductApi
    lateinit var productsListView : ListView
    lateinit var editTxtFilter : EditText
    lateinit var btnSearch : Button

    companion object Product
    {
        var selectedProduct : com.example.idnow_v2application.product.Product? = null // Listedeki seçili elemanı statik olarak tutuyorum detay sayfasında çağırıcam.
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productsListView = findViewById(R.id.productsListView)
        editTxtFilter = findViewById(R.id.editTxtFilter)
        btnSearch = findViewById(R.id.btnSearch)


        productApi = NetworkModule.buildRetrofit(createOkHttpClient(),createGsonConverterFactory()).create(
            ProductApi::class.java)
        productApi.Products(10).enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                val datas = response.body()
                val customAdapter = CustomProductsListAdapter(this@ProductListActivity,datas!!.products)
                productsListView.adapter = customAdapter
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Log.e("Error",t.toString())
            }

        })

        productsListView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedItem = productsListView.getItemAtPosition(i) as com.example.idnow_v2application.product.Product
            selectedProduct = selectedItem
            var intent = Intent(this@ProductListActivity, ProductDetailActivity::class.java)
            startActivity(intent)


        }

        btnSearch.setOnClickListener{
            productApi.Filter(editTxtFilter.text.toString()).enqueue(object : Callback<ProductsResponse>
            {
                override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                    val searchData = response.body()
                    val myAdapter = CustomProductsListAdapter(this@ProductListActivity,searchData!!.products)
                    productsListView.adapter =  myAdapter
                }

                override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                    Log.e("SearchError",t.toString())
                }

            })
        }

    }
}