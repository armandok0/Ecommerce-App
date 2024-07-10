package com.example.ecommerceapp.fragments.menu

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.adapters.ProfileAdapter
import com.example.ecommerceapp.data.OrderViewModel
import com.example.ecommerceapp.data.Product
import com.example.ecommerceapp.data.ProductViewModel
import com.example.ecommerceapp.databinding.FragmentProfileBinding
import com.example.ecommerceapp.activities.MainActivity
import java.util.Locale

class ProfileFragment : Fragment() {
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: ProfileAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderViewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        adapter = ProfileAdapter(object : ProfileAdapter.ProfileItemClickListener {
        }, object : ProductAdapter.ReviewSubmitListener {
            override fun onReviewSubmit(productId: Int, rating: Float, comment: String) {
                if (rating > 0 && comment.isNotBlank()) {
                    submitReview(productId, rating, comment)
                } else {
                    showMessage("Both rating and comment are required to submit a review.")
                }
            }
        })

        binding.recyclerViewOrders.adapter = adapter
        binding.recyclerViewOrders.layoutManager = LinearLayoutManager(requireContext())

        orderViewModel.allOrders.observe(viewLifecycleOwner) { orders ->
            if (orders.isEmpty()) {
                binding.textViewNoOrders.visibility = View.VISIBLE
                binding.recyclerViewOrders.visibility = View.GONE
            } else {
                binding.textViewNoOrders.visibility = View.GONE
                binding.recyclerViewOrders.visibility = View.VISIBLE
                adapter.submitList(orders)
            }
        }


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        binding.buttonChangeLanguage.setOnClickListener {
            // Toggle language
            val currentLanguage = sharedPreferences.getString("app_language", "en")
            val newLanguage = if (currentLanguage == "en") "el" else "en"
            changeLanguage(newLanguage)
        }
    }

    private fun changeLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)

        with(sharedPreferences.edit()) {
            putString("app_language", languageCode)
            apply()
        }

        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun submitReview(productId: Int, rating: Float, comment: String) {
        val productObserver = Observer<Product> { product ->
            if (isReviewAlreadyAdded(product, rating, comment)) {
                return@Observer
            }

            val updatedRatings = product.reviewRatings.toMutableList()
            updatedRatings.add(rating)

            val updatedComments = product.reviewComments.toMutableList()
            updatedComments.add(comment)

            val updatedProduct = product.copy(
                reviewRatings = updatedRatings,
                reviewComments = updatedComments
            )

            productViewModel.updateProduct(updatedProduct)

            showMessage("Review submitted successfully!")
        }

        productViewModel.getProductById(productId).observe(this, productObserver)
    }

    private fun isReviewAlreadyAdded(product: Product, rating: Float, comment: String): Boolean {
        val existingRatings = product.reviewRatings
        val existingComments = product.reviewComments

        for (i in existingRatings.indices) {
            if (existingRatings[i] == rating && existingComments[i] == comment) {
                return true
            }
        }
        return false
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
