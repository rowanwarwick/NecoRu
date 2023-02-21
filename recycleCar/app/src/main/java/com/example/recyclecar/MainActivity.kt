package com.example.recyclecar

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclecar.adapter.CarAdapter
import com.example.recyclecar.data.Car
import com.example.recyclecar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val adapter = CarAdapter()
    var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                adapter.addCar(it.data?.getSerializableExtra("car") as Car)
            }
        }
        init()
    }

    fun init() {
        binding.apply {
            list.adapter = adapter
            add.setOnClickListener {
                launcher?.launch(Intent(this@MainActivity, EditActivity::class.java))
            }
        }
    }
}