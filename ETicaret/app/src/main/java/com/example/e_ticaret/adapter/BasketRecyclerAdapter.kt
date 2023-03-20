package com.example.e_ticaret.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.e_ticaret.databinding.BasketRecyclerRowBinding
import com.example.e_ticaret.model.Product

class BasketRecyclerAdapter(val basketList: List<Product>) :RecyclerView.Adapter<BasketRecyclerAdapter.BasketViewHolder>() {
    /*class BasketViewHolder(itemview: View): ViewHolder(itemview){
    }*/
    class BasketViewHolder(val binding: BasketRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding=BasketRecyclerRowBinding.inflate(LayoutInflater.from((parent.context)),parent,false)
        return BasketViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return basketList.size
   }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        with(holder.binding)
        {
            basketProductNameText.text=basketList.get(position).name
            basketCountText.text="ADET : ${basketList.get(position).count} "
            basketPriceText.text= "Fiyat: ${basketList.get(position).price}"
            Glide.with(basketImageView.context).load(basketList.get(position).url).into(basketImageView)
        }

        }
}