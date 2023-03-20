package com.example.e_ticaret.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_ticaret.R
import com.example.e_ticaret.adapter.ProductRecyclerAdapter
import com.example.e_ticaret.databinding.FragmentProductsBinding
import com.example.e_ticaret.model.Product
import com.example.e_ticaret.viewModel.ProductViewModel


class ProductsFragment : Fragment(), ProductRecyclerAdapter.Listener {

    private lateinit var binding: FragmentProductsBinding

    //Tek bir viewmodeli İki fragment için bağlayacaksak
    private val productViewModel: ProductViewModel by activityViewModels()
    private var productRecyclerAdapter: ProductRecyclerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
        /*// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager=GridLayoutManager(activity?.baseContext,2)

        productViewModel.downloadData()
        productViewModel.productList.observe(viewLifecycleOwner, Observer {
            productRecyclerAdapter = ProductRecyclerAdapter(it, this)
            binding.recyclerView.adapter = productRecyclerAdapter
        })

    }

    override fun onItemClick(product: Product) {
//Sepete ekleme işleri
        productViewModel.addToBasket(product)
    }


}