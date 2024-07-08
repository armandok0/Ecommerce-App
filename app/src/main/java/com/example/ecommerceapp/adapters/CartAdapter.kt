package com.example.ecommerceapp.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Cart

class CartAdapter(private val listener: CartItemClickListener) :
    ListAdapter<Cart, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    interface CartItemClickListener {
        fun onDecreaseQuantityClick(cartItem: Cart, position: Int)
        fun onIncreaseQuantityClick(cartItem: Cart, position: Int)
        fun onDeleteItemClick(cartItem: Cart, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

        holder.btnDecrease.setOnClickListener {
            listener.onDecreaseQuantityClick(currentItem, position)
        }

        holder.btnIncrease.setOnClickListener {
            listener.onIncreaseQuantityClick(currentItem, position)
        }

        holder.btnDelete.setOnClickListener {
            listener.onDeleteItemClick(currentItem, position)
        }
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val btnDecrease: ImageButton = itemView.findViewById(R.id.btnDecrease)
        val textViewQuantity: TextView = itemView.findViewById(R.id.textViewQuantity)
        val btnIncrease: ImageButton = itemView.findViewById(R.id.btnIncrease)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        val textViewColor: TextView = itemView.findViewById(R.id.textViewColor)
        val textViewSize: TextView = itemView.findViewById(R.id.textViewSize)

        fun bind(cartItem: Cart) {
            imageViewProduct.setImageResource(cartItem.imageResId)
            textViewProductName.text = cartItem.productName
            textViewCategory.text = cartItem.category.category
            textViewPrice.text = "${cartItem.price} $"
            textViewQuantity.text = cartItem.quantity.toString()

            // Set color and size information
            val hexColor = cartItem.selectedColor?.let { String.format("#%06X", 0xFFFFFF and it) } ?: "#000000"
            textViewColor.text = "Color:"

            try {
                val color = Color.parseColor(hexColor)
                val circleDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.cart_circle)
                circleDrawable?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                textViewColor.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, circleDrawable, null)
            } catch (e: IllegalArgumentException) {
                // Handle error if color parsing fails
                val circleDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.cart_circle)
                circleDrawable?.setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP) // Transparent color
                textViewColor.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, circleDrawable, null)
            }

            textViewSize.text = "Size: ${cartItem.selectedSize ?: "N/A"}"
        }



    }

    class CartDiffCallback : DiffUtil.ItemCallback<Cart>() {
        override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }
    }
}
