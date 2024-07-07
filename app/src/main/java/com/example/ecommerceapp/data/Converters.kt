package com.example.ecommerceapp.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListInt(list: List<Int>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun toListInt(data: String?): List<Int>? {
        if (data.isNullOrBlank()) {
            return emptyList()
        }
        return data.split(",").mapNotNull { it.toIntOrNull() }
    }

    @TypeConverter
    fun fromListString(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun toListString(data: String?): List<String>? {
        return data?.split(",")
    }

    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.category
    }

    @TypeConverter
    fun toCategory(category: String): Category {
        return Category.fromString(category)
    }


}
