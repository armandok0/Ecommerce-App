package com.example.ecommerceapp.data

import com.example.ecommerceapp.R

class ProductDataSource {

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
                imageResIds = listOf(
                    R.drawable.home_product_sofa1, R.drawable.home_product_sofa1
                ),
                quantity = 10
            ),
            Product(
                id = 2,
                name = "Dining Table",
                category = Category.Tables,
                price = 499.99f,
                offerPercentage = null,
                description = "A large wooden dining table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFBC8F8F.toInt(), 0xFF2F4F4F.toInt()), // Saddle Brown, Rosy Brown, Dark Slate Gray
                sizes = listOf("M", "L"), // Assuming "6-Seater" as Medium and "8-Seater" as Large
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 5
            ),
            Product(
                id = 3,
                name = "Office Chair",
                category = Category.Chairs,
                price = 149.99f,
                offerPercentage = 5f,
                description = "An ergonomic office chair.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF556B2F.toInt(), 0xFF20B2AA.toInt()), // Steel Blue, Dark Olive Green, Light Sea Green
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 20
            ),
            Product(
                id = 4,
                name = "Bookshelf",
                category = Category.Decor,
                price = 199.99f,
                offerPercentage = null,
                description = "A wooden bookshelf.",
                colors = listOf(0xFFA52A2A.toInt(), 0xFF6B8E23.toInt(), 0xFF9370DB.toInt()), // Brown, Olive Drab, Medium Purple
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 15
            ),
            Product(
                id = 5,
                name = "Bed Frame",
                category = Category.Beds,
                price = 399.99f,
                offerPercentage = 15f,
                description = "A queen-sized bed frame.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()), // Saddle Brown, Orchid, Slate Blue
                sizes = listOf("L"), // Assuming "Queen" as Large
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 8
            ),
            Product(
                id = 6,
                name = "Coffee Table",
                category = Category.Tables,
                price = 149.99f,
                offerPercentage = 0f,
                description = "A small coffee table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFD2B48C.toInt(), 0xFF556B2F.toInt()), // Saddle Brown, Tan, Dark Olive Green
                sizes = listOf("S"), // Assuming "Small" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 18
            ),
            Product(
                id = 7,
                name = "Desk Lamp",
                category = Category.Decor,
                price = 39.99f,
                offerPercentage = null,
                description = "A modern desk lamp.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF000000.toInt(), 0xFFFFFFFF.toInt()), // Steel Blue, Black, White
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 25
            ),
            Product(
                id = 8,
                name = "Accent Chair",
                category = Category.Chairs,
                price = 199.99f,
                offerPercentage = 0f,
                description = "A stylish accent chair.",
                colors = listOf(0xFF800080.toInt(), 0xFFD2691E.toInt(), 0xFF9370DB.toInt()), // Purple, Chocolate, Medium Purple
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 12
            ),
            Product(
                id = 9,
                name = "Throw Pillow",
                category = Category.Decor,
                price = 19.99f,
                offerPercentage = null,
                description = "A decorative throw pillow.",
                colors = listOf(0xFFFF0000.toInt(), 0xFF00FF00.toInt(), 0xFF0000FF.toInt()), // Red, Lime, Blue
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 30
            ),
            Product(
                id = 10,
                name = "Nightstand",
                category = Category.Tables,
                price = 79.99f,
                offerPercentage = 0f,
                description = "A bedside nightstand.",
                colors = listOf(0xFF8B4513.toInt(), 0xFF000000.toInt(), 0xFFD2B48C.toInt()), // Saddle Brown, Black, Tan
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 15
            ),
            Product(
                id = 11,
                name = "Throw Blanket",
                category = Category.Decor,
                price = 29.99f,
                offerPercentage = null,
                description = "A cozy throw blanket.",
                colors = listOf(0xFFFF0000.toInt(), 0xFF00FF00.toInt(), 0xFF0000FF.toInt()), // Red, Lime, Blue
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 25
            ),
            Product(
                id = 12,
                name = "Cabinet",
                category = Category.Decor,
                price = 299.99f,
                offerPercentage = null,
                description = "A stylish storage cabinet.",
                colors = listOf(0xFFA52A2A.toInt(), 0xFF8B4513.toInt(), 0xFFD2691E.toInt()), // Brown, Saddle Brown, Chocolate
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 10
            ),
            Product(
                id = 13,
                name = "End Table",
                category = Category.Tables,
                price = 99.99f,
                offerPercentage = 0f,
                description = "A small end table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFD2B48C.toInt(), 0xFF556B2F.toInt()), // Saddle Brown, Tan, Dark Olive Green
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 20
            ),
            Product(
                id = 14,
                name = "Rug",
                category = Category.Decor,
                price = 149.99f,
                offerPercentage = null,
                description = "A patterned area rug.",
                colors = listOf(0xFFFF0000.toInt(), 0xFF00FF00.toInt(), 0xFF0000FF.toInt()), // Red, Lime, Blue
                sizes = listOf("S", "L"), // Assuming "Small" and "Large"
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 15
            ),
            Product(
                id = 15,
                name = "Console Table",
                category = Category.Tables,
                price = 199.99f,
                offerPercentage = null,
                description = "A sleek console table.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFD2B48C.toInt(), 0xFF556B2F.toInt()), // Saddle Brown, Tan, Dark Olive Green
                sizes = listOf("S"), // Assuming "Standard" as Small
                imageResIds = listOf(
                    R.drawable.home_product_sofa1
                ),
                quantity = 12
            )
        )
    }
}
