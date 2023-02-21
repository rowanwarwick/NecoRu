package com.example.fragments.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val messageToFrag2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageToFrag1: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageToActiv: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}