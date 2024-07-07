package com.example.ecommerceapp.fragments.categories

import com.example.ecommerceapp.adapters.HomeBaseProductsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Category
import com.example.ecommerceapp.data.ProductViewModel

open class BaseCategoryFragment(private val category: Category) : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productsAdapter: HomeBaseProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_base_category, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.baseProducts)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productsAdapter = HomeBaseProductsAdapter(requireContext())
        recyclerView.adapter = productsAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.getProductsByCategory(category).observe(viewLifecycleOwner, Observer { products ->
            productsAdapter.submitList(products)
        })

        productsAdapter.onClick = { product ->
            val bundle = Bundle().apply {
                putInt("productId", product.id)
            }
            findNavController().navigate(R.id.productDetailsFragment, bundle)
        }
    }
}
