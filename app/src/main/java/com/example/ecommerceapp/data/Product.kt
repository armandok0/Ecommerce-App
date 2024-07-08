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
    val reviewRatings: List<Float> = listOf(), // List to store review ratings
    val reviewComments: List<String> = listOf() // List to store review comments
)
