package com.example.fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.fragments.databinding.FragmentBlankBinding
import com.example.fragments.viewModel.DataModel

class BlankFragment : Fragment() {

    lateinit var binding: FragmentBlankBinding
    val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.messageToFrag1.observe(activity as LifecycleOwner) {
            binding.message.text = it
        }
        binding.buttonToActiv.setOnClickListener {
            dataModel.messageToActiv.value = "Hello activity from fragment 1"
        }
        binding.buttonToFrag2.setOnClickListener {
            dataModel.messageToFrag2.value = "Hello frag 2 from fragment 1"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}