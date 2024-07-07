// FavoriteViewModel.kt
package com.example.ecommerceapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.Favorite
import com.example.ecommerceapp.data.FavoriteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteDao = FavoriteDatabase.getDatabase(application).favoriteDao()
    val allFavoriteItems: LiveData<List<Favorite>> = favoriteDao.getAllFavoriteItemsLiveData()

    fun addToFavorites(favoriteItem: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.insertFavoriteItem(favoriteItem)
        }
    }

    fun removeFromFavorites(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.deleteFavoriteItem(productId)
        }
    }

    suspend fun isProductFavorited(productId: Int): Boolean {
        return favoriteDao.isProductFavorited(productId) > 0
    }
}

