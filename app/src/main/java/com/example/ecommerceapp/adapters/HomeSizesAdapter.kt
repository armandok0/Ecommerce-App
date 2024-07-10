package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R

class HomeSizesAdapter : RecyclerView.Adapter<HomeSizesAdapter.SizesViewHolder>() {

    private var selectedPosition = -1

    inner class SizesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSize: TextView = itemView.findViewById(R.id.tvSize)
        private val imageShadow: ImageView = itemView.findViewById(R.id.imageShadow)

        fun bind(size: String, position: Int) {
            tvSize.text = size
            if (position == selectedPosition) {
                imageShadow.visibility = View.VISIBLE
            } else { // Size is not selected
                imageShadow.visibility = View.INVISIBLE
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_product_details_sizes, parent, false)
        return SizesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SizesViewHolder, position: Int) {
        val size = differ.currentList[position]
        holder.bind(size, position)

        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(size)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onItemClick: ((String) -> Unit)? = null
}
