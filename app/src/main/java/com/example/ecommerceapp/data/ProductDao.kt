package com.example.ecommerceapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Product>)

    @Query("SELECT * FROM Product WHERE category = :category")
    fun getProductsByCategory(category: String): LiveData<List<Product>>

    @Query("SELECT * FROM Product LIMIT 5")
    fun getFirstFiveProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Product WHERE offerPercentage IS NOT NULL AND offerPercentage > 0")
    fun getProductsWithOfferPercentage(): LiveData<List<Product>>

    @Query("SELECT * FROM Product ")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Product WHERE id = :productId")
    fun getProductById(productId: Int): LiveData<Product>

    @Update
    suspend fun updateProduct(product: Product)

    @Query("""
        SELECT * FROM Product 
        WHERE (:category IS NULL OR category = :category)
        AND (:maxPrice IS NULL OR price <= :maxPrice)
        AND (:nameQuery IS NULL OR name LIKE '%' || :nameQuery || '%')
    """)
    fun getFilteredProducts(category: String?, maxPrice: Float?, nameQuery: String?): LiveData<List<Product>>
}
