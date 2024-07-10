package com.example.ecommerceapp.fragments.menu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.HomeBaseProductsAdapter
import com.example.ecommerceapp.data.ProductViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var productsAdapter: HomeBaseProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchInput = view.findViewById<EditText>(R.id.searchInput)
        val categoryFilter = view.findViewById<EditText>(R.id.categoryFilter)
        val priceFilter = view.findViewById<EditText>(R.id.priceFilter)
        val ratingFilter = view.findViewById<EditText>(R.id.ratingFilter)
        val applyFiltersButton = view.findViewById<Button>(R.id.applyFiltersButton)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val noResultsTextView = view.findViewById<TextView>(R.id.noResultsTextView)

        productsAdapter = HomeBaseProductsAdapter(requireContext())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = productsAdapter

        applyFiltersButton.setOnClickListener {
            val category = categoryFilter.text.toString().takeIf { it.isNotEmpty() }
            val maxPrice = priceFilter.text.toString().toFloatOrNull()
            val minRating = ratingFilter.text.toString().toFloatOrNull()
            val nameQuery = searchInput.text.toString().takeIf { it.isNotEmpty() }

            productViewModel.getFilteredProducts(category, maxPrice, minRating, nameQuery)
                .observe(viewLifecycleOwner, Observer { products ->
                    if (products.isEmpty()) {
                        recyclerView.visibility = View.GONE
                        noResultsTextView.visibility = View.VISIBLE
                    } else {
                        recyclerView.visibility = View.VISIBLE
                        noResultsTextView.visibility = View.GONE
                        productsAdapter.submitList(products)
                    }
                })
        }

        val searchIcon = view.findViewById<ImageView>(R.id.searchIcon)
        searchIcon.setOnClickListener {
            val query = searchInput.text.toString().takeIf { it.isNotEmpty() }
            productViewModel.getFilteredProducts(null, null, null, query)
                .observe(viewLifecycleOwner, Observer { products ->
                    if (products.isEmpty()) {
                        recyclerView.visibility = View.GONE
                        noResultsTextView.visibility = View.VISIBLE
                    } else {
                        recyclerView.visibility = View.VISIBLE
                        noResultsTextView.visibility = View.GONE
                        productsAdapter.submitList(products)
                    }
                })
        }

        val initialQuery = searchInput.text.toString().takeIf { it.isNotEmpty() }
        productViewModel.getFilteredProducts(null, null, null, initialQuery)
            .observe(viewLifecycleOwner, Observer { products ->
                if (products.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    noResultsTextView.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    noResultsTextView.visibility = View.GONE
                    productsAdapter.submitList(products)
                }
            })
    }
}
