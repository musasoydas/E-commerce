package com.example.e_ticaret.model

data class Product(
    val id:String,
    var name:String,
    var price: String,
    var url: String
) {
    var count =0
}