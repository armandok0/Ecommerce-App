package com.example.ecommerceapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM order_items")
    fun getAllOrders(): LiveData<List<Order>>
}
