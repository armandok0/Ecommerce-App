package com.example.ecommerceapp.fragments.menu

import android.os.Bundle
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

class ProfileFragment : Fragment() {
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        orderViewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        // Setup RecyclerView for Orders
        adapter = ProfileAdapter(object : ProfileAdapter.ProfileItemClickListener {
            // Implement any interface methods if needed
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
            adapter.submitList(orders)
        }
    }

    private fun submitReview(productId: Int, rating: Float, comment: String) {
        // Define the observer
        val productObserver = Observer<Product> { product ->
            // Check if the review already exists
            if (isReviewAlreadyAdded(product, rating, comment)) {
                showMessage("You have already submitted a review with the same rating and comment.")
                // Re-enable UI element here if necessary
                return@Observer
            }

            // Create updated lists
            val updatedRatings = product.reviewRatings.toMutableList()
            updatedRatings.add(rating)

            val updatedComments = product.reviewComments.toMutableList()
            updatedComments.add(comment)

            // Create updated product
            val updatedProduct = product.copy(
                reviewRatings = updatedRatings,
                reviewComments = updatedComments
            )

            // Update the product in the database
            productViewModel.updateProduct(updatedProduct)

            // Show success message
            showMessage("Review submitted successfully!")


            // Re-enable UI element here if necessary
        }

        // Observe the product
        productViewModel.getProductById(productId).observe(viewLifecycleOwner, productObserver)
    }


    private fun isReviewAlreadyAdded(product: Product, rating: Float, comment: String): Boolean {
        // Check if the review with the same rating and comment already exists
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
