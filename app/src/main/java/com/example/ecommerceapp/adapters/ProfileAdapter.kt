package com.example.ecommerceapp.adapters

import ProductAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.data.Order
import com.example.ecommerceapp.databinding.ItemOrderBinding

class ProfileAdapter(
    private val listener: ProfileItemClickListener,
    private val reviewListener: ProductAdapter.ReviewSubmitListener
) : RecyclerView.Adapter<ProfileAdapter.OrderViewHolder>() {

    interface ProfileItemClickListener {
        // Define any interactions or callbacks here if needed
    }

    private var orders: List<Order> = emptyList()

    fun submitList(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = orders.size

    inner class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        private val textViewOrderDate = binding.textViewOrderDate
        private val textViewOrderTotal = binding.textViewOrderTotal
        private val recyclerViewOrderProducts = binding.recyclerViewOrderProducts

        fun bind(order: Order) {
            textViewOrderDate.text = "Order Date: ${order.timestamp}"
            textViewOrderTotal.text = "Total: $${order.totalPrice}"

            // Setup LinearLayoutManager for recyclerViewOrderProducts
            recyclerViewOrderProducts.layoutManager = LinearLayoutManager(itemView.context)

            // Setup RecyclerView for order products using ProductAdapter
            val productAdapter = ProductAdapter(order.cartItems, reviewListener)
            recyclerViewOrderProducts.adapter = productAdapter
        }
    }
}
