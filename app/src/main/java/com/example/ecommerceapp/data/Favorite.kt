package com.example.ecommerceapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "favorite_items")
@TypeConverters(Converters::class)
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    val productName: String,
    val category: Category,
    val price: Float,
    val imageResId: Int
) {
    companion object {
        fun fromProduct(product: Product): Favorite {
            return Favorite(
                productId = product.id,
                productName = product.name,
                category = product.category,
                price = product.price,
                imageResId = product.imageResIds.firstOrNull() ?: 0
            )
        }
    }
}
