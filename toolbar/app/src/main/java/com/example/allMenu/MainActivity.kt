package com.example.allMenu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.allMenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navigate.setOnItemSelectedListener {
            when(it.itemId){
                R.id.icon1 -> Toast.makeText(this, "icon1", Toast.LENGTH_SHORT).show()
                R.id.icon2 -> Toast.makeText(this, "icon2", Toast.LENGTH_SHORT).show()
                R.id.icon3 -> Toast.makeText(this, "icon3", Toast.LENGTH_SHORT).show()
                R.id.icon4 -> Toast.makeText(this, "icon4", Toast.LENGTH_SHORT).show()
            }
            return@setOnItemSelectedListener true
        }
        binding.open.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.item_1 -> Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show()
                R.id.item_2 -> Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show()
                R.id.item_3 -> Toast.makeText(this, "item3", Toast.LENGTH_SHORT).show()
                R.id.item_4 -> Toast.makeText(this, "item4", Toast.LENGTH_SHORT).show()
                R.id.item_11 -> Toast.makeText(this, "item11", Toast.LENGTH_SHORT).show()
                R.id.item_21 -> Toast.makeText(this, "item21", Toast.LENGTH_SHORT).show()
                R.id.item_31 -> Toast.makeText(this, "item31", Toast.LENGTH_SHORT).show()
                R.id.item_41 -> Toast.makeText(this, "item41", Toast.LENGTH_SHORT).show()
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.delete -> Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            R.id.save -> Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
            R.id.search -> Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
            R.id.sync -> Toast.makeText(this, "sync", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}