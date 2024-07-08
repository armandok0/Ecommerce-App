package com.example.ecommerceapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val orderDao: OrderDao = OrderDatabase.getDatabase(application).orderDao()

    val allOrders: LiveData<List<Order>> = orderDao.getAllOrders()

    suspend fun insert(order: Order) {
        orderDao.insertOrder(order)
    }
}
