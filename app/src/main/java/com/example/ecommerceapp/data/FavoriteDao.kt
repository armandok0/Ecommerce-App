package com.example.ecommerceapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceapp.data.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoriteItem: Favorite)

    @Query("SELECT * FROM favorite_items")
    fun getAllFavoriteItemsLiveData(): LiveData<List<Favorite>>

    @Query("DELETE FROM favorite_items WHERE productId = :productId")
    suspend fun deleteFavoriteItem(productId: Int)

    @Query("SELECT COUNT(*) FROM favorite_items WHERE productId = :productId")
    suspend fun isProductFavorited(productId: Int): Int
}
