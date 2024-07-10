package com.example.ecommerceapp.data

import androidx.room.TypeConverters
import com.example.ecommerceapp.R

class ProductDataSource {

    private val defaultImageResId = R.drawable.home_product_sofa1

    fun getFurnitureProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Μοντέρνος Καναπές",
                category = Category.Sofas,
                price = 299.99f,
                offerPercentage = 10f,
                description = "Ένας άνετος μοντέρνος καναπές.",
                colors = listOf(0xFFC0C0C0.toInt(), 0xFF8B4513.toInt(), 0xFF6A5ACD.toInt()), // Ασημί, Saddle Brown, Slate Blue
                sizes = listOf("S", "M", "L"),
                imageResIds = listOf(defaultImageResId),
                quantity = 10,
                reviewRatings = listOf(4.5f, 5f, 3.5f), // Παραδείγματα αξιολογήσεων
                reviewComments = listOf(
                    "Πολύ άνετος και κομψός!",
                    "Μεγάλος καναπές αλλά λίγο ακριβός.",
                    "Όμορφος σχεδιασμός αλλά θα μπορούσε να είναι πιο μαλακός."
                )
            ),
            Product(
                id = 2,
                name = "Τραπέζι Φαγητού",
                category = Category.Tables,
                price = 499.99f,
                offerPercentage = null,
                description = "Ένα μεγάλο ξύλινο τραπέζι φαγητού.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFBC8F8F.toInt(), 0xFF2F4F4F.toInt()), // Saddle Brown, Rosy Brown, Dark Slate Gray
                sizes = listOf("M", "L"),
                imageResIds = listOf(defaultImageResId),
                quantity = 5,
                reviewRatings = listOf(4f, 4.5f), // Παραδείγματα αξιολογήσεων
                reviewComments = listOf(
                    "Ακριβή κατασκευή αλλά η συναρμολόγηση ήταν δύσκολη.",
                    "Όμορφο τραπέζι, ταιριάζει τέλεια στην τραπεζαρία μας."
                )
            ),
            // Εδώ μπορείτε να προσθέσετε κι άλλα προϊόντα...
        )
    }
}
