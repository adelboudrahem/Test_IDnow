package com.example.idnow_v2application.product

import com.google.gson.annotations.SerializedName


data class ProductsResponse(
    @SerializedName("products") val products: List<Product>,
    @SerializedName("total") val total: Long,
    @SerializedName("skip")val skip: Long,
    @SerializedName("limit")val limit: Long
)
data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("discountPercentage") val discountPercentage: Double,
    @SerializedName("rating") val rating: Double,
    @SerializedName("stock") val stock: Int,
    @SerializedName("brand") val brand: String,
    @SerializedName("category") val category: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("images") val images: List<String>
)
