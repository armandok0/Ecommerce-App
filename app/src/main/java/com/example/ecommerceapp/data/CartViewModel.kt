package com.example.ecommerceapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.Cart
import com.example.ecommerceapp.data.CartDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val cartDao = CartDatabase.getDatabase(application).cartDao()
    val allCartItems: LiveData<List<Cart>> = cartDao.getAllCartItemsLiveData()

    fun addToCart(cartItem: Cart) {
        viewModelScope.launch {
            val existingItem = cartDao.getCartItemByProductColorSize(cartItem.productName, cartItem.selectedColor, cartItem.selectedSize)
            if (existingItem != null) {
                // Update quantity if item with same color and size exists
                existingItem.quantity += cartItem.quantity
                cartDao.updateCartItem(existingItem)
            } else {
                // Insert new item if no matching item found
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
}
