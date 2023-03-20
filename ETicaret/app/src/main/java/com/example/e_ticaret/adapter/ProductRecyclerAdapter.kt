package com.example.e_ticaret.adapter

import android.content.ContentResolver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_ticaret.R
import com.example.e_ticaret.databinding.RecyclerRowBinding
import com.example.e_ticaret.model.Product
import kotlinx.coroutines.withContext

class ProductRecyclerAdapter(val productList: List<Product>, val listener: Listener): RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>(){
   class ProductHolder( val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        /*val view =LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ProductHolder(view)*/
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductHolder(binding)
        // ProductHolder(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return productList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        with(holder.binding){
            productName.text= productList.get(position).name
            productPrice.text= productList.get(position).price
            Glide.with(productImage.context).load(productList.get(position).url).into(productImage)

            addBasketButton.setOnClickListener {
                Toast.makeText(it.context,"SEPETE EKLENDİ ${productList.get(position).name}",Toast.LENGTH_LONG).show()
                listener.onItemClick(productList.get(position))
            }
        }


    }

    interface Listener{
        fun onItemClick(product: Product)
    }
}



