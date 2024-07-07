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

class HomeDealsProductsAdapter(private val context: Context) : RecyclerView.Adapter<HomeDealsProductsAdapter.DealProductViewHolder>() {

    private var dealsProducts: List<Product> = emptyList()

    inner class DealProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBestDeal: ImageView = itemView.findViewById(R.id.Img_Best_Deal)
        val tvDealProductName: TextView = itemView.findViewById(R.id.tv_deal_product_name)
        val tvOldPrice: TextView = itemView.findViewById(R.id.Deals_Product_Old_Price)
        val tvNewPrice: TextView = itemView.findViewById(R.id.Deals_Product_New_Price)
        val btnSeeProduct: Button = itemView.findViewById(R.id.Btn_See_Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_home_deals_item, parent, false)
        return DealProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: DealProductViewHolder, position: Int) {
        val product = dealsProducts[position]

        if (product.imageResIds.isNotEmpty()) {
            holder.imgBestDeal.setImageResource(product.imageResIds[0])
        }

        holder.tvDealProductName.text = product.name

        holder.tvOldPrice.text = "$${String.format(Locale.US, "%.2f", product.price)}"
        holder.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        product.offerPercentage?.let { offer ->
            val discountedPrice = product.price * (1 - offer / 100)
            if (discountedPrice < product.price) {
                holder.tvNewPrice.text = "$${String.format(Locale.US, "%.2f", discountedPrice)}"
            } else {
                holder.tvNewPrice.text = ""
            }
        } ?: run {
            holder.tvNewPrice.text = ""
        }

        holder.btnSeeProduct.setOnClickListener {
            onClick?.invoke(product)
        }

        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return dealsProducts.size
    }

    fun submitList(newList: List<Product>) {
        dealsProducts = newList
        notifyDataSetChanged()
    }

    var onClick: ((Product) -> Unit)? = null
}
