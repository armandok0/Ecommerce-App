package com.example.ecommerceapp.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.adapters.ProfileAdapter
import com.example.ecommerceapp.data.OrderViewModel
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

        // Observe LiveData from ViewModel
        orderViewModel.allOrders.observe(viewLifecycleOwner, { orders ->
            // Update the RecyclerView with new data
            adapter.submitList(orders)
        })
    }

    private fun submitReview(productId: Int, rating: Float, comment: String) {
        showMessage("Review submitted successfully!")
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
