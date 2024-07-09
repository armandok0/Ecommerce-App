package com.example.ecommerceapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity
@TypeConverters(Converters::class)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: Category,
    val price: Float,
    val offerPercentage: Float?,
    val description: String,
    val colors: List<Int>,
    val sizes: List<String>,
    val imageResIds: List<Int>,
    val quantity: Int,
    val reviewRatings: List<Float> = listOf(),
    val reviewComments: List<String> = listOf()
) {
    fun getAverageRating(): Float {
        if (reviewRatings.isEmpty()) {
            return 0f
        }
        val sum = reviewRatings.sum()
        return sum / reviewRatings.size
    }
}
