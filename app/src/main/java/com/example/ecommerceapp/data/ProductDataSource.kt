package com.example.ecommerceapp.data

import androidx.room.TypeConverters
import com.example.ecommerceapp.R

class ProductDataSource {

    fun getFurnitureProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Modern Sofa",
                category = Category.Sofas,
                price = 299.99f,
                offerPercentage = 0f,
                description = "A comfortable modern sofa.",
                colors = listOf(0xFFC0C0C0.toInt(), 0xFF8B4513.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("S", "M", "L"),
                imageResIds = listOf(R.drawable.product_sofa_1, R.drawable.product_sofa_1_2, R.drawable.product_sofa_1_3),
                quantity = 10,
                reviewRatings = listOf(4.5f, 5f),
                reviewComments = listOf(
                    "Very comfortable and stylish!",
                    "Great sofa but a bit expensive."
                )
            ),
            Product(
                id = 2,
                name = "Classic Sofa",
                category = Category.Sofas,
                price = 279.99f,
                offerPercentage = 12f,
                description = "A classic, timeless sofa.",
                colors = listOf(0xFFC0C0C0.toInt(), 0xFF8B4513.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("S", "M", "L"),
                imageResIds = listOf(R.drawable.product_sofa_2, R.drawable.product_sofa_2_1),
                quantity = 15,
                reviewRatings = listOf(4f, 4.5f),
                reviewComments = listOf(
                    "Beautiful design very comfortable.",
                    "Affordable and stylish."
                )
            ),
            Product(
                id = 3,
                name = "Support Chair",
                category = Category.Chairs,
                price = 149.99f,
                offerPercentage = 0f,
                description = "An ergonomic chair.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF556B2F.toInt(), 0xFF20B2AA.toInt()),
                sizes = listOf("S"),
                imageResIds = listOf(R.drawable.product_chair_1, R.drawable.product_chair_1_1),
                quantity = 20,
                reviewRatings = listOf(3.5f, 4f),
                reviewComments = listOf(
                    "Good back support but seat could be more comfortable.",
                    "Great value for money."
                )
            ),
            Product(
                id = 4,
                name = "Luxury Chair",
                category = Category.Chairs,
                price = 199.99f,
                offerPercentage = 8f,
                description = "A luxurious and comfortable chair.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF556B2F.toInt(), 0xFF20B2AA.toInt()),
                sizes = listOf("M", "L"),
                imageResIds = listOf(R.drawable.product_chair_2, R.drawable.product_chair_2_1),
                quantity = 10,
                reviewRatings = listOf(4f, 4.5f),
                reviewComments = listOf(
                    "Very comfortable, high quality.",
                    "Stylish design fits well in my living room."
                )
            ),
            Product(
                id = 5,
                name = "Queen Bed",
                category = Category.Beds,
                price = 399.99f,
                offerPercentage = 15f,
                description = "A queen-sized bed frame.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("L"),
                imageResIds = listOf(R.drawable.product_bed_1, R.drawable.product_bed_1_1),
                quantity = 8,
                reviewRatings = listOf(4f, 3.5f),
                reviewComments = listOf(
                    "Solid frame easy to assemble.",
                    "Nice design but a bit pricey."
                )
            ),
            Product(
                id = 6,
                name = "Wooden Table",
                category = Category.Tables,
                price = 249.99f,
                offerPercentage = 10f,
                description = "A sturdy wooden dining table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFA52A2A.toInt(), 0xFFDEB887.toInt()),
                sizes = listOf("M", "L"),
                imageResIds = listOf(R.drawable.product_table_1, R.drawable.product_table_1_2),
                quantity = 12,
                reviewRatings = listOf(4.5f, 4f),
                reviewComments = listOf(
                    "High quality and great finish.",
                    "Perfect size for my dining room."
                )
            ),
            Product(
                id = 7,
                name = "Modern Table",
                category = Category.Tables,
                price = 199.99f,
                offerPercentage = 5f,
                description = "A modern coffee table with glass top.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFB0C4DE.toInt(), 0xFF2F4F4F.toInt()),
                sizes = listOf("S", "M"),
                imageResIds = listOf(R.drawable.product_table_2, R.drawable.product_table_2_1),
                quantity = 10,
                reviewRatings = listOf(4.5f, 4.5f),
                reviewComments = listOf(
                    "Looks great in my living room.",
                    "Stylish and functional."
                )
            ),
            Product(
                id = 8,
                name = "Minimalist Table",
                category = Category.Tables,
                price = 99.99f,
                offerPercentage = 10f,
                description = "A minimalist end table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDAA520.toInt(), 0xFFCD853F.toInt()),
                sizes = listOf("S"),
                imageResIds = listOf(R.drawable.product_table_3, R.drawable.products_table_3_1),
                quantity = 15,
                reviewRatings = listOf(4f, 3.5f),
                reviewComments = listOf(
                    "Simple and elegant.",
                    "Great value for the price."
                )
            ),
            Product(
                id = 9,
                name = "Wall Decor",
                category = Category.Decor,
                price = 79.99f,
                offerPercentage = 20f,
                description = "A beautiful artistic wall decor piece.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("M"),
                imageResIds = listOf(R.drawable.product_decor_1, R.drawable.product_decor_1_1),
                quantity = 25,
                reviewRatings = listOf(4.5f, 4f),
                reviewComments = listOf(
                    "Looks amazing on my wall.",
                    "High quality and vibrant colors."
                )
            ),
            Product(
                id = 10,
                name = "Vintage Leaf",
                category = Category.Decor,
                price = 49.99f,
                offerPercentage = 0f,
                description = "A vintage-style Leaf.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("S"),
                imageResIds = listOf(R.drawable.product_decor_2, R.drawable.product_decor_2_1),
                quantity = 30,
                reviewRatings = listOf(4f),
                reviewComments = listOf(
                    "Beautiful vintage design."
                )
            )
        )
    }
}
