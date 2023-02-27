package com.example.sqliteroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.sqliteroom.DAO.Item
import com.example.sqliteroom.DB.MainDB
import com.example.sqliteroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataBase = MainDB.getDB(this)
        dataBase.getData().getAllItem().asLiveData().observe(this){list ->
            binding.text.text = ""
            list.forEach() {
                val text = "Id: ${it.id} name: ${it.name} value: ${it.value}\n"
                binding.text.append(text)
            }
        }
        binding.save.setOnClickListener {
            val item = Item(
                null,
                binding.name.text.toString(),
                binding.price.text.toString()
            )
            Thread {
                dataBase.getData().insertItem(item)
            }.start()
        }
    }
}