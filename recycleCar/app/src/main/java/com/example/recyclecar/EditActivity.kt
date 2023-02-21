package com.example.recyclecar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclecar.data.Car
import com.example.recyclecar.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    var index = 0
    val listCarImage = listOf(R.drawable.bmw, R.drawable.honda, R.drawable.lexus, R.drawable.opel, R.drawable.toyota, R.drawable.vw)
    var image = R.drawable.bmw

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {
        binding.apply {
            next.setOnClickListener {
                index = (index + 1) % listCarImage.size
                image = listCarImage[index]
                imageView.setImageResource(image)
            }
            previous.setOnClickListener {
                index = (index - 1 + listCarImage.size) % listCarImage.size
                image = listCarImage[index]
                imageView.setImageResource(image)
            }
            add.setOnClickListener {
                val intent = Intent()
                intent.putExtra("car", Car(image, titleCar.text.toString(), descCar.text.toString()))
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}