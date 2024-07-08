package com.example.ecommerceapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {

    @Insert
    suspend fun insertCartItem(cartItem: Cart)

    @Update
    suspend fun updateCartItem(cartItem: Cart)

    @Delete
    suspend fun deleteCartItem(cartItem: Cart)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItemsLiveData(): LiveData<List<Cart>>

    @Query("SELECT * FROM cart_items WHERE productName = :productName AND selectedColor = :color AND selectedSize = :size")
    suspend fun getCartItemByProductColorSize(productName: String, color: Int?, size: String?): Cart?


    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<Cart>

}
