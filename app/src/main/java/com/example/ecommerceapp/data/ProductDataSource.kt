package com.example.ecommerceapp.data

import androidx.room.TypeConverters
import com.example.ecommerceapp.R

class ProductDataSource {

    private val defaultImageResId = R.drawable.home_product_sofa1

    fun getFurnitureProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Modern Sofa",
                category = Category.Sofas,
                price = 299.99f,
                offerPercentage = 10f,
                description = "A comfortable modern sofa.",
                colors = listOf(0xFFC0C0C0.toInt(), 0xFF8B4513.toInt(), 0xFF6A5ACD.toInt()), // Silver, Saddle Brown, Slate Blue
                sizes = listOf("S", "M", "L"),
                imageResIds = listOf(defaultImageResId),
                quantity = 10,
                reviewRatings = listOf(4.5f, 5f, 3.5f), // Example review ratings
                reviewComments = listOf(
                    "Very comfortable and stylish!",
                    "Great sofa but a bit expensive.",
                    "Nice design but could be more cushioned."
                )
            ),
            Product(
                id = 2,
                name = "Dining Table",
                category = Category.Tables,
                price = 499.99f,
                offerPercentage = null,
                description = "A large wooden dining table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFBC8F8F.toInt(), 0xFF2F4F4F.toInt()), // Saddle Brown, Rosy Brown, Dark Slate Gray
                sizes = listOf("M", "L"),
                imageResIds = listOf(defaultImageResId),
                quantity = 5,
                reviewRatings = listOf(4f, 4.5f), // Example review ratings
                reviewComments = listOf(
                    "Solid construction but assembly was tricky.",
                    "Beautiful table fits perfectly in our dining room."
                )
            ),
            Product(
                id = 3,
                name = "Office Chair",
                category = Category.Chairs,
                price = 149.99f,
                offerPercentage = 5f,
                description = "An ergonomic office chair.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF556B2F.toInt(), 0xFF20B2AA.toInt()), // Steel Blue, Dark Olive Green, Light Sea Green
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 20,
                reviewRatings = listOf(3.5f, 4f, 3f), // Example review ratings
                reviewComments = listOf(
                    "Good back support, but seat could be more comfortable.",
                    "Great value for money.",
                    "Sturdy chair, easy to assemble."
                )
            ),
            Product(
                id = 4,
                name = "Bookshelf",
                category = Category.Decor,
                price = 199.99f,
                offerPercentage = null,
                description = "A wooden bookshelf.",
                colors = listOf(0xFFA52A2A.toInt(), 0xFF6B8E23.toInt(), 0xFF9370DB.toInt()), // Brown, Olive Drab, Medium Purple
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 15,
                reviewRatings = listOf(4.5f), // Example review ratings
                reviewComments = listOf(
                    "Looks great in my living room!",
                    "Solid construction."
                )
            ),
            Product(
                id = 5,
                name = "Bed Frame",
                category = Category.Beds,
                price = 399.99f,
                offerPercentage = 15f,
                description = "A queen-sized bed frame.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()), // Saddle Brown, Orchid, Slate Blue
                sizes = listOf("L"),
                imageResIds = listOf(defaultImageResId),
                quantity = 8,
                reviewRatings = listOf(4f, 3.5f), // Example review ratings
                reviewComments = listOf(
                    "Solid frame, easy to assemble.",
                    "Nice design, but a bit pricey."
                )
            ),
            Product(
                id = 6,
                name = "Coffee Table",
                category = Category.Tables,
                price = 149.99f,
                offerPercentage = 0f,
                description = "A small coffee table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFD2B48C.toInt(), 0xFF556B2F.toInt()), // Saddle Brown, Tan, Dark Olive Green
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 18,
                reviewRatings = listOf(3.5f, 3f), // Example review ratings
                reviewComments = listOf(
                    "Fits perfectly in our living room.",
                    "Sturdy table, but scratches easily."
                )
            ),
            Product(
                id = 7,
                name = "Desk Lamp",
                category = Category.Decor,
                price = 39.99f,
                offerPercentage = null,
                description = "A modern desk lamp.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF000000.toInt(), 0xFFFFFFFF.toInt()), // Steel Blue, Black, White
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 25,
                reviewRatings = listOf(4.5f, 4f), // Example review ratings
                reviewComments = listOf(
                    "Sleek design, provides good light.",
                    "Good value for money."
                )
            ),
            Product(
                id = 8,
                name = "Accent Chair",
                category = Category.Chairs,
                price = 199.99f,
                offerPercentage = 0f,
                description = "A stylish accent chair.",
                colors = listOf(0xFF800080.toInt(), 0xFFD2691E.toInt(), 0xFF9370DB.toInt()), // Purple, Chocolate, Medium Purple
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 12,
                reviewRatings = listOf(3.5f, 4f), // Example review ratings
                reviewComments = listOf(
                    "Beautiful chair, but a bit small.",
                    "Comfortable and looks great in our living room."
                )
            ),
            Product(
                id = 9,
                name = "Throw Pillow",
                category = Category.Decor,
                price = 19.99f,
                offerPercentage = null,
                description = "A decorative throw pillow.",
                colors = listOf(0xFFFF0000.toInt(), 0xFF00FF00.toInt(), 0xFF0000FF.toInt()), // Red, Lime, Blue
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 30,
                reviewRatings = listOf(4f, 3.5f, 4.5f), // Example review ratings
                reviewComments = listOf(
                    "Nice colors, good quality.",
                    "Soft and comfortable.",
                    "Looks great on our sofa."
                )
            ),
            Product(
                id = 10,
                name = "Nightstand",
                category = Category.Tables,
                price = 79.99f,
                offerPercentage = 0f,
                description = "A bedside nightstand.",
                colors = listOf(0xFF8B4513.toInt(), 0xFF000000.toInt(), 0xFFD2B48C.toInt()), // Saddle Brown, Black, Tan
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 15,
                reviewRatings = listOf(3.5f, 4f), // Example review ratings
                reviewComments = listOf(
                    "Solid construction, but a bit small.",
                    "Fits perfectly next to our bed."
                )
            ),
            Product(
                id = 11,
                name = "Throw Blanket",
                category = Category.Decor,
                price = 29.99f,
                offerPercentage = null,
                description = "A cozy throw blanket.",
                colors = listOf(0xFFFF0000.toInt(), 0xFF00FF00.toInt(), 0xFF0000FF.toInt()), // Red, Lime, Blue
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 25,
                reviewRatings = listOf(4.5f, 4f), // Example review ratings
                reviewComments = listOf(
                    "Soft and warm.",
                    "Nice colors, good size."
                )
            ),
            Product(
                id = 12,
                name = "Cabinet",
                category = Category.Decor,
                price = 299.99f,
                offerPercentage = null,
                description = "A stylish storage cabinet.",
                colors = listOf(0xFFA52A2A.toInt(), 0xFF8B4513.toInt(), 0xFFD2691E.toInt()), // Brown, Saddle Brown, Chocolate
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 10,
                reviewRatings = listOf(4f, 3.5f), // Example review ratings
                reviewComments = listOf(
                    "Beautiful design, but assembly was tricky.",
                    "Solid construction, good value."
                )
            ),
            Product(
                id = 13,
                name = "End Table",
                category = Category.Tables,
                price = 99.99f,
                offerPercentage = 0f,
                description = "A small end table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFD2B48C.toInt(), 0xFF556B2F.toInt()), // Saddle Brown, Tan, Dark Olive Green
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 20,
                reviewRatings = listOf(3.5f, 4f), // Example review ratings
                reviewComments = listOf(
                    "Sturdy and well-made.",
                    "Perfect size for our living room."
                )
            ),
            Product(
                id = 14,
                name = "Rug",
                category = Category.Decor,
                price = 149.99f,
                offerPercentage = null,
                description = "A patterned area rug.",
                colors = listOf(0xFFFF0000.toInt(), 0xFF00FF00.toInt(), 0xFF0000FF.toInt()), // Red, Lime, Blue
                sizes = listOf("S", "L"),
                imageResIds = listOf(defaultImageResId),
                quantity = 15,
                reviewRatings = listOf(4f, 3.5f), // Example review ratings
                reviewComments = listOf(
                    "Nice design, good quality.",
                    "Soft and comfortable."
                )
            ),
            Product(
                id = 15,
                name = "Console Table",
                category = Category.Tables,
                price = 199.99f,
                offerPercentage = null,
                description = "A sleek console table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFD2B48C.toInt(), 0xFF556B2F.toInt()), // Saddle Brown, Tan, Dark Olive Green
                sizes = listOf("S"),
                imageResIds = listOf(defaultImageResId),
                quantity = 12,
                reviewRatings = listOf(4.5f, 4f), // Example review ratings
                reviewComments = listOf(
                    "Looks great in our hallway.",
                    "Sturdy and well-made."
                )
            )
        )
    }
}
