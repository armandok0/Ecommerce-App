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
    // other existing fields remain unchanged
    val cartItems: List<Cart>,  // New field to store cart items as a list
    val timestamp: Long,
    val totalPrice: Float
) {
    companion object {
        // Update the existing fromCart function to accommodate cartItems list
        fun fromCart(cartItems: List<Cart>, totalPrice: Float): Order {
            // Assuming other fields are already set in the Cart to Order conversion
            // Here, productId, productName, category, etc., are not required as list of Cart has it all
            return Order(
                cartItems = cartItems,
                timestamp = System.currentTimeMillis(),
                totalPrice = totalPrice
            )
        }
    }
}

