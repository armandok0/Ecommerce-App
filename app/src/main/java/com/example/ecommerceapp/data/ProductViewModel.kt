package com.example.ecommerceapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.Category
import com.example.ecommerceapp.data.Product
import com.example.ecommerceapp.data.ProductDatabase
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

}

