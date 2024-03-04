package com.example.idnow_v2application.network

import com.example.idnow_v2application.product.ProductsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ProductApi  {

    @Headers("Content-Type: application/json")
    @GET("products")
    fun Products(@Query("limit") limit: Int): Call<ProductsResponse>

    @GET("products/search")
    fun Filter(@Query("q") keyword: String): Call<ProductsResponse>

    @POST("auth/login")
    fun login(@Body jwtUser: JWTUser): Call<JWTData>

}