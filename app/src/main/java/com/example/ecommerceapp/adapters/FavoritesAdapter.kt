package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Favorite

class FavoriteAdapter(private val onItemClick: (Int) -> Unit, private val onFavoriteClick: (Favorite) -> Unit) : ListAdapter<Favorite, FavoriteAdapter.FavoriteViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteItem = getItem(position)
        holder.bind(favoriteItem)
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        private val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        private val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        private val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        private val btnSeeProduct: Button = itemView.findViewById(R.id.btnSeeProduct)
        private val imgFavorite: ImageView = itemView.findViewById(R.id.imgFavorite)

        fun bind(favorite: Favorite) {
            imageViewProduct.setImageResource(favorite.imageResId)
            textViewProductName.text = favorite.productName
            textViewCategory.text = favorite.category.category
            textViewPrice.text = favorite.price.toString()


            btnSeeProduct.setOnClickListener {
                onItemClick(favorite.productId)
            }

            imgFavorite.setOnClickListener {
                onFavoriteClick(favorite)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }
}
