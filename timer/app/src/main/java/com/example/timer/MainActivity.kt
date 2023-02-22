package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.start.setOnClickListener {
            startTimer()
        }
    }

    private fun startTimer(time: Long = 2000) {
        timer = object : CountDownTimer(time, 1) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.timer.text = "финиш"
            }

        }.start()
    }
}