package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R

class HomeViewerPager2Adapter : RecyclerView.Adapter<HomeViewerPager2Adapter.ViewPager2ImagesViewHolder>() {

    inner class ViewPager2ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageProductDetails: ImageView = itemView.findViewById(R.id.imageProductDetails)

        fun bind(imageResId: Int) {
            Glide.with(itemView).load(imageResId).into(imageProductDetails)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2ImagesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_image_item, parent, false)
        return ViewPager2ImagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPager2ImagesViewHolder, position: Int) {
        val image = differ.currentList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
