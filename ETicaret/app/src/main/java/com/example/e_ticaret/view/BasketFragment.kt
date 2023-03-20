package com.example.e_ticaret.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_ticaret.R
import com.example.e_ticaret.adapter.BasketRecyclerAdapter
import com.example.e_ticaret.databinding.FragmentBasketBinding
import com.example.e_ticaret.databinding.FragmentProductsBinding
import com.example.e_ticaret.viewModel.ProductViewModel

class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    private var basketRecyclerAdapter: BasketRecyclerAdapter?=null
    private val swipeCallBack= object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition=viewHolder.layoutPosition
            if(basketRecyclerAdapter!= null){
                val selectedProduct= basketRecyclerAdapter!!.basketList.get(layoutPosition)
                productViewModel.deleteProductFromBasket(selectedProduct)
                basketRecyclerAdapter!!.notifyItemRemoved(layoutPosition)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_basket, container, false)

        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.basketRecyclerView.layoutManager=LinearLayoutManager(activity?.baseContext)
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.basketRecyclerView)
        productViewModel.basket.observe(viewLifecycleOwner, Observer {
            basketRecyclerAdapter=BasketRecyclerAdapter(it)
            binding.basketRecyclerView.adapter=basketRecyclerAdapter
        })

        productViewModel.totalBasket.observe(viewLifecycleOwner, Observer {
            binding.totalBasketText.text="Toplam Tutar:  ${it}"
        })
    }


}