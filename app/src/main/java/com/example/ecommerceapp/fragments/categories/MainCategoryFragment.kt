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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.ProductViewModel
import com.example.ecommerceapp.adapters.HomeDealsProductsAdapter

class MainCategoryFragment : Fragment() {

    private lateinit var homeDealsProductsAdapter: HomeDealsProductsAdapter
    private lateinit var homeBaseProductsAdapter: HomeBaseProductsAdapter
    private lateinit var rvDealsProducts: RecyclerView
    private lateinit var rvBestProducts: RecyclerView
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_main_category, container, false)

        rvDealsProducts = rootView.findViewById(R.id.rvBestDealsProducts)
        rvBestProducts = rootView.findViewById(R.id.rvBestProducts)

        setupDealsProductsRecyclerView()
        setupBestProductsRecyclerView()

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        productViewModel.bestProducts.observe(viewLifecycleOwner, Observer { products ->
            homeDealsProductsAdapter.submitList(products)
        })

        productViewModel.specialProducts.observe(viewLifecycleOwner, Observer { products ->
            homeBaseProductsAdapter.submitList(products)
        })

        homeDealsProductsAdapter.onClick = { product ->
            val bundle = Bundle().apply {
                putInt("productId", product.id)
            }
            findNavController().navigate(R.id.productDetailsFragment, bundle)
        }

        homeBaseProductsAdapter.onClick = { product ->
            val bundle = Bundle().apply {
                putInt("productId", product.id)
            }
            findNavController().navigate(R.id.productDetailsFragment, bundle)
        }

        return rootView
    }

    private fun setupDealsProductsRecyclerView() {
        homeDealsProductsAdapter = HomeDealsProductsAdapter(requireContext())
        rvDealsProducts.apply {
            adapter = homeDealsProductsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setupBestProductsRecyclerView() {
        homeBaseProductsAdapter = HomeBaseProductsAdapter(requireContext())
        rvBestProducts.apply {
            adapter = homeBaseProductsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }
}
