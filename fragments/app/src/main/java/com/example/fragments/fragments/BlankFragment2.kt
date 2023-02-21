package com.example.fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.fragments.databinding.FragmentBlank2Binding
import com.example.fragments.viewModel.DataModel

class BlankFragment2 : Fragment() {

    lateinit var binding: FragmentBlank2Binding
    val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlank2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.messageToFrag2.observe(activity as LifecycleOwner) {
            binding.message.text = it
        }
        binding.buttonToActiv.setOnClickListener {
            dataModel.messageToActiv.value = "Hello activity from fragment 2"
        }
        binding.buttonToFrag1.setOnClickListener {
            dataModel.messageToFrag1.value = "Hello frag 1 from fragment 2"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment2()
    }
}