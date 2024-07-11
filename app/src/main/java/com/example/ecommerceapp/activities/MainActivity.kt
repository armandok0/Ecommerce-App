package com.example.ecommerceapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.ProductDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        // Check if there is a new language set and populate the appropriate database
        val newLanguage = intent.getStringExtra("new_language")
        if (newLanguage != null) {
            if (newLanguage == "el") {
                ProductDatabase.populateGreekDatabase(this)
            } else {
                ProductDatabase.populateDatabase(this)
            }
        } else {
            // Default database
            ProductDatabase.populateDatabase(this)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.shoppingHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setupWithNavController(navController)
    }
}
