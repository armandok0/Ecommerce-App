package com.example.ecommerceapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val cartDao = CartDatabase.getDatabase(application).cartDao()
    private val orderDao = OrderDatabase.getDatabase(application).orderDao()
    val allCartItems: LiveData<List<Cart>> = cartDao.getAllCartItemsLiveData()

    fun addToCart(cartItem: Cart) {
        viewModelScope.launch {
            val existingItem = cartDao.getCartItemByProductColorSize(cartItem.productName, cartItem.selectedColor, cartItem.selectedSize)
            if (existingItem != null) {
                existingItem.quantity += cartItem.quantity
                cartDao.updateCartItem(existingItem)
            } else {
                cartDao.insertCartItem(cartItem)
            }
        }
    }

    fun updateCartItem(cartItem: Cart) {
        viewModelScope.launch {
            cartDao.updateCartItem(cartItem)
        }
    }

    fun deleteCartItem(cartItem: Cart) {
        viewModelScope.launch {
            cartDao.deleteCartItem(cartItem)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartDao.clearCart()
        }
    }

    fun placeOrderAndClearCart() {
        viewModelScope.launch(Dispatchers.IO) {
            val cartItems = cartDao.getAllCartItems()
            val totalPrice = calculateTotalPrice(cartItems)
            val order = Order.fromCart(cartItems, totalPrice)
            orderDao.insertOrder(order)
            cartDao.clearCart()
        }
    }

    private fun calculateTotalPrice(cartItems: List<Cart>): Float {
        var totalPrice = 0f
        for (cartItem in cartItems) {
            totalPrice += cartItem.price * cartItem.quantity
        }
        return totalPrice
    }
}
