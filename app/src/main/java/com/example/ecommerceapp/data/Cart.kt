package com.example.ecommerceapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "cart_items")
@TypeConverters(Converters::class)
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    val productName: String,
    val category: Category,
    val price: Float,
    var quantity: Int,
    val selectedColor: Int?,
    val selectedSize: String?,
    val imageResId: Int
) {
    companion object {
        fun fromProduct(product: Product, quantity: Int, selectedColor: Int?, selectedSize: String?): Cart {
            return Cart(
                productId = product.id,
                productName = product.name,
                category = product.category,
                price = product.price,
                quantity = quantity,
                selectedColor = selectedColor,
                selectedSize = selectedSize,
                imageResId = product.imageResIds.firstOrNull() ?: 0
            )
        }
    }
}
