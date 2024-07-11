package com.example.ecommerceapp.adapters

import ProductAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Order
import com.example.ecommerceapp.databinding.ItemOrderBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileAdapter(
    private val listener: ProfileItemClickListener,
    private val reviewSubmitListener: ProductAdapter.ReviewSubmitListener
) : RecyclerView.Adapter<ProfileAdapter.OrderViewHolder>() {

    interface ProfileItemClickListener {
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
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(order.timestamp))

            textViewOrderDate.text = itemView.context.getString(R.string.order_date_text, formattedDate)
            textViewOrderTotal.text = itemView.context.getString(R.string.total_price_text, order.totalPrice.toString())

            recyclerViewOrderProducts.layoutManager = LinearLayoutManager(itemView.context)

            val productAdapter = ProductAdapter(order.cartItems, reviewSubmitListener)
            recyclerViewOrderProducts.adapter = productAdapter
        }
    }
}
