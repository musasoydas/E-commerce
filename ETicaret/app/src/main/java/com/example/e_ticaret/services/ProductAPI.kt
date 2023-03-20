package com.example.e_ticaret.services

import com.example.e_ticaret.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    // https://raw.githubusercontent.com/atilsamancioglu/BTK23-DataSet/main/products.json
    // base url -> https://raw.githubusercontent.com/


    @GET("atilsamancioglu/BTK23-DataSet/main/products.json")
    suspend fun getData(): Response<List<Product>>


}