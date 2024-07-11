package com.example.ecommerceapp.data

import androidx.room.TypeConverters
import com.example.ecommerceapp.R

class ProductGreekDataSource {

    fun getFurnitureProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Σύγχρονος Καναπές",
                category = Category.Sofas,
                price = 299.99f,
                offerPercentage = 0f,
                description = "Ένας άνετος σύγχρονος καναπές.",
                colors = listOf(0xFFC0C0C0.toInt(), 0xFF8B4513.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("S", "M", "L"),
                imageResIds = listOf(R.drawable.product_sofa_1, R.drawable.product_sofa_1_2, R.drawable.product_sofa_1_3),
                quantity = 10,
                reviewRatings = listOf(4.5f, 5f),
                reviewComments = listOf(
                    "Πολύ άνετος και κομψός!",
                    "Εξαιρετικός καναπές αλλά λίγο ακριβός."
                )
            ),
            Product(
                id = 2,
                name = "Κλασικός Καναπές",
                category = Category.Sofas,
                price = 279.99f,
                offerPercentage = 12f,
                description = "Ένας κλασικός, απαράμιλλος καναπές.",
                colors = listOf(0xFFC0C0C0.toInt(), 0xFF8B4513.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("S", "M", "L"),
                imageResIds = listOf(R.drawable.product_sofa_2, R.drawable.product_sofa_2_1),
                quantity = 15,
                reviewRatings = listOf(4f, 4.5f),
                reviewComments = listOf(
                    "Όμορφος σχεδιασμός και πολύ άνετος.",
                    "Οικονομικός και κομψός."
                )
            ),
            Product(
                id = 3,
                name = "Εργονομική Καρέκλα",
                category = Category.Chairs,
                price = 149.99f,
                offerPercentage = 0f,
                description = "Μια εργονομική καρέκλα.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF556B2F.toInt(), 0xFF20B2AA.toInt()),
                sizes = listOf("S"),
                imageResIds = listOf(R.drawable.product_chair_1, R.drawable.product_chair_1_1),
                quantity = 20,
                reviewRatings = listOf(3.5f, 4f),
                reviewComments = listOf(
                    "Καλή υποστήριξη στην πλάτη αλλά θα μπορούσε η κάθιση να είναι πιο άνετη.",
                    "Πολύ καλή αξία για τα χρήματα."
                )
            ),
            Product(
                id = 4,
                name = "Πολυτελής Καρέκλα",
                category = Category.Chairs,
                price = 199.99f,
                offerPercentage = 8f,
                description = "Μια πολυτελής και άνετη καρέκλα.",
                colors = listOf(0xFF4682B4.toInt(), 0xFF556B2F.toInt(), 0xFF20B2AA.toInt()),
                sizes = listOf("M", "L"),
                imageResIds = listOf(R.drawable.product_chair_2, R.drawable.product_chair_2_1),
                quantity = 10,
                reviewRatings = listOf(4f, 4.5f),
                reviewComments = listOf(
                    "Πολύ άνετη, υψηλής ποιότητας.",
                    "Στυλάτος σχεδιασμός ταιριάζει τέλεια στο σαλόνι μου."
                )
            ),
            Product(
                id = 5,
                name = "Κρεβάτι Queen",
                category = Category.Beds,
                price = 399.99f,
                offerPercentage = 15f,
                description = "Ένα κρεβάτι queen size.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("L"),
                imageResIds = listOf(R.drawable.product_bed_1, R.drawable.product_bed_1_1),
                quantity = 8,
                reviewRatings = listOf(4f, 3.5f),
                reviewComments = listOf(
                    "Σταθερός σκελετός εύκολος στη συναρμολόγηση.",
                    "Όμορφος σχεδιασμός αλλά λίγο ακριβός."
                )
            ),
            Product(
                id = 6,
                name = "Ξύλινο Τραπέζι",
                category = Category.Tables,
                price = 249.99f,
                offerPercentage = 10f,
                description = "Ένα ανθεκτικό ξύλινο τραπέζι φαγητού.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFA52A2A.toInt(), 0xFFDEB887.toInt()),
                sizes = listOf("M", "L"),
                imageResIds = listOf(R.drawable.product_table_1, R.drawable.product_table_1_2),
                quantity = 12,
                reviewRatings = listOf(4.5f, 4f),
                reviewComments = listOf(
                    "Υψηλής ποιότητας και εξαιρετικό φινίρισμα.",
                    "Τέλειο μέγεθος για την τραπεζαρία μου."
                )
            ),
            Product(
                id = 7,
                name = "Μοντέρνο Τραπέζι",
                category = Category.Tables,
                price = 199.99f,
                offerPercentage = 5f,
                description = "Ένα μοντέρνο τραπεζάκι καφέ με γυαλί στην κορυφή.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFB0C4DE.toInt(), 0xFF2F4F4F.toInt()),
                sizes = listOf("S", "M"),
                imageResIds = listOf(R.drawable.product_table_2, R.drawable.product_table_2_1),
                quantity = 10,
                reviewRatings = listOf(4.5f, 4.5f),
                reviewComments = listOf(
                    "Φαίνεται υπέροχο στο σαλόνι μου.",
                    "Στυλάτο και λειτουργικό."
                )
            ),
            Product(
                id = 8,
                name = "Μινιμαλιστικό Τραπέζι",
                category = Category.Tables,
                price = 99.99f,
                offerPercentage = 10f,
                description = "Ένα μινιμαλιστικό τραπέζι σαλονιού.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDAA520.toInt(), 0xFFCD853F.toInt()),
                sizes = listOf("S"),
                imageResIds = listOf(R.drawable.product_table_3, R.drawable.products_table_3_1),
                quantity = 15,
                reviewRatings = listOf(4f, 3.5f),
                reviewComments = listOf(
                    "Απλό και κομψό.",
                    "Πολύ καλή αξία για τα χρήματα."
                )
            ),
            Product(
                id = 9,
                name = "Διακοσμητικό Τοίχου",
                category = Category.Decor,
                price = 79.99f,
                offerPercentage = 20f,
                description = "Ένα όμορφο καλλιτεχνικό διακοσμητικό τοίχου.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("M"),
                imageResIds = listOf(R.drawable.product_decor_1, R.drawable.product_decor_1_1),
                quantity = 25,
                reviewRatings = listOf(4.5f, 4f),
                reviewComments = listOf(
                    "Φαίνεται εκπληκτικό στον τοίχο μου.",
                    "Υψηλής ποιότητας και ζωηρά χρώματα."
                )
            ),
            Product(
                id = 10,
                name = "Βιντάζ Φύλλο",
                category = Category.Decor,
                price = 49.99f,
                offerPercentage = 0f,
                description = "Ένα βιντάζ στυλ φύλλο.",
                colors = listOf(0xFF8B4513.toInt(), 0xFFDA70D6.toInt(), 0xFF6A5ACD.toInt()),
                sizes = listOf("S"),
                imageResIds = listOf(R.drawable.product_decor_2, R.drawable.product_decor_2_1),
                quantity = 30,
                reviewRatings = listOf(4f),
                reviewComments = listOf(
                    "Όμορφο βιντάζ σχέδιο."
                )
            )
        )
    }
}
