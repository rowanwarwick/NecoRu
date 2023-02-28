package com.example.navigationviewnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationviewnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var configuration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.menu.openDrawer(GravityCompat.START)
        setSupportActionBar(binding.toolbar)
        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        configuration = AppBarConfiguration(setOf(R.id.page1, R.id.page2), binding.menu)
        setupActionBarWithNavController(navController, configuration)
        binding.navigationMenu.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(configuration) || super.onSupportNavigateUp()
    }
}