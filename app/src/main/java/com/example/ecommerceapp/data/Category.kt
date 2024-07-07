package com.example.ecommerceapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Category(val category: String) : Parcelable {
    object Sofas : Category("Sofas")
    object Tables : Category("Tables")
    object Chairs : Category("Chairs")
    object Beds : Category("Beds")
    object Decor : Category("Decor")

    companion object {
        fun fromString(category: String): Category {
            return when (category) {
                Sofas.category -> Sofas
                Tables.category -> Tables
                Chairs.category -> Chairs
                Beds.category -> Beds
                Decor.category -> Decor
                else -> throw IllegalArgumentException("Unknown category: $category")
            }
        }
    }
}
