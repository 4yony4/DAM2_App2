package org.eurekamps.dam2_app2.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeActivityVIewModel: ViewModel() {

    // LiveData or MutableLiveData for the string variable
    private val _sharedString = MutableLiveData<String>()
    val sharedString: LiveData<String> get() = _sharedString

    // Function to set the value
    fun setString(value: String) {
        _sharedString.value = value
    }

}