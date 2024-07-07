package com.example.ecommerceapp.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R

class HomeColorsAdapter : RecyclerView.Adapter<HomeColorsAdapter.ColorsViewHolder>() {

    private var selectedPosition = -1

    inner class ColorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageColor: ImageView = itemView.findViewById(R.id.imageColor)
        private val imageShadow: ImageView = itemView.findViewById(R.id.imageShadow)
        private val imagePicked: ImageView = itemView.findViewById(R.id.imagePicked)

        fun bind(color: Int, position: Int) {
            val imageDrawable = ColorDrawable(color)
            imageColor.setImageDrawable(imageDrawable)
            if (position == selectedPosition) {
                imageShadow.visibility = View.VISIBLE
                imagePicked.visibility = View.VISIBLE
            } else {
                imageShadow.visibility = View.INVISIBLE
                imagePicked.visibility = View.INVISIBLE
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_product_details_color, parent, false)
        return ColorsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val color = differ.currentList[position]
        holder.bind(color, position)

        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(color)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onItemClick: ((Int) -> Unit)? = null
}
