package com.example.ecommerceapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "order_items")
@TypeConverters(Converters::class)
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cartItems: List<Cart>,
    val timestamp: Long,
    val totalPrice: Float
) {
    companion object {
        fun fromCart(cartItems: List<Cart>, totalPrice: Float): Order {
            return Order(
                cartItems = cartItems,
                timestamp = System.currentTimeMillis(),
                totalPrice = totalPrice
            )
        }
    }
}

