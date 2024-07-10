package com.example.ecommerceapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val productDao = ProductDatabase.getDatabase(application).productDao()
    val specialProducts: LiveData<List<Product>> = productDao.getFirstFiveProducts()
    val bestProducts: LiveData<List<Product>> = productDao.getProductsWithOfferPercentage()

    fun getProductsByCategory(category: Category): LiveData<List<Product>> {
        return productDao.getProductsByCategory(category.category)
    }

    private lateinit var product: LiveData<Product>

    fun getProductById(productId: Int): LiveData<Product> {
        product = productDao.getProductById(productId)
        return product
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productDao.updateProduct(product)
        }
    }

    fun getFilteredProducts(category: String?, maxPrice: Float?, minRating: Float?, nameQuery: String?): LiveData<List<Product>> {
        val result = MediatorLiveData<List<Product>>()

        viewModelScope.launch(Dispatchers.Main) {
            val source = productDao.getFilteredProducts(category, maxPrice, nameQuery)
            result.addSource(source) { products ->
                val filteredList = products.filter { it.getAverageRating() >= (minRating ?: 0f) }
                result.value = filteredList
            }
        }

        return result
    }
}
