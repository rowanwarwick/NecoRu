package com.example.navigationviewnavigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationviewnavigation.R
import com.example.navigationviewnavigation.databinding.FragmentNewYorkBinding

class NewYorkFragment : Fragment() {

    lateinit var binding: FragmentNewYorkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewYorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewYorkFragment()
    }
}