package com.example.ecommerceapp.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Product
import java.util.*

class HomeBaseProductsAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeBaseProductsAdapter.ProductViewHolder>() {

    private var products: List<Product> = emptyList()
    var onClick: ((Product) -> Unit)? = null

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.Product_Image)
        val tvProductName: TextView = itemView.findViewById(R.id.Product_Name)
        val tvPrice: TextView = itemView.findViewById(R.id.Product_Old_Price)
        val tvNewPrice: TextView = itemView.findViewById(R.id.Deals_Product_New_Price)
        val btnSeeProduct: Button = itemView.findViewById(R.id.Btn_See_The_Product)
        val tvRating: TextView = itemView.findViewById(R.id.Product_Rating)

        init {
            btnSeeProduct.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick?.invoke(products[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_home_product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.tvProductName.text = product.name

        holder.tvPrice.text = "$${String.format(Locale.US, "%.2f", product.price)}"

        product.offerPercentage?.let { offer ->
            val discountedPrice = product.price * (1 - offer / 100)
            if (discountedPrice < product.price) {
                holder.tvNewPrice.visibility = View.VISIBLE
                holder.tvNewPrice.text = "$${String.format(Locale.US, "%.2f", discountedPrice)}"
                holder.tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                holder.tvNewPrice.visibility = View.GONE
                holder.tvPrice.paintFlags = 0
            }
        } ?: run {
            holder.tvNewPrice.visibility = View.GONE
            holder.tvPrice.paintFlags = 0
        }

        val averageRating = product.getAverageRating()
        holder.tvRating.text = String.format(Locale.US, "%.1f", averageRating)

        if (product.imageResIds.isNotEmpty()) {
            holder.imgProduct.setImageResource(product.imageResIds[0])
        } else {
            // Set a placeholder image or handle empty case
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun submitList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}
