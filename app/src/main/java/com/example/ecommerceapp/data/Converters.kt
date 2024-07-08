package com.example.ecommerceapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.util.Date

class Converters {

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapterFactory(CategoryTypeAdapterFactory())
        .create()

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
    fun fromListFloat(list: List<Float>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toListFloat(data: String?): List<Float>? {
        return gson.fromJson(data, object : TypeToken<List<Float>>() {}.type)
    }

    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.category
    }

    @TypeConverter
    fun toCategory(category: String): Category {
        return Category.fromString(category)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millis: Long?): Date? {
        return millis?.let { Date(it) }
    }

    @TypeConverter
    fun fromCartList(cartItems: List<Cart>?): String? {
        return gson.toJson(cartItems)
    }

    @TypeConverter
    fun toCartList(cartItemsString: String?): List<Cart>? {
        return gson.fromJson(cartItemsString, object : TypeToken<List<Cart>>() {}.type)
    }

    // TypeAdapterFactory for Category subclasses
    private class CategoryTypeAdapterFactory : TypeAdapterFactory {
        override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
            val rawType = type?.rawType as Class<T>
            if (Category::class.java.isAssignableFrom(rawType)) {
                return object : TypeAdapter<T>() {
                    @Throws(IOException::class)
                    override fun write(out: JsonWriter?, value: T) {
                        out?.value((value as Category).category)
                    }

                    @Throws(IOException::class)
                    override fun read(`in`: JsonReader?): T {
                        val categoryString = `in`?.nextString()
                        return categoryString?.let { Category.fromString(it) } as T
                    }
                }
            }
            return null
        }
    }
}
