package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.ActivityMainBinding
import com.example.fragments.fragments.BlankFragment
import com.example.fragments.fragments.BlankFragment2
import com.example.fragments.viewModel.DataModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFrag(BlankFragment.newInstance(), R.id.fragment1)
        openFrag(BlankFragment2.newInstance(), R.id.fragment2)
        dataModel.messageToActiv.observe(this) {
            binding.textView.text = it
        }
    }

    fun openFrag(fragment: Fragment, idHolder: Int){
        supportFragmentManager.beginTransaction().replace(idHolder, fragment).commit()
    }
}