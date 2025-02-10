package com.example.aplicaciones.ui.notifications



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {  // ✅ CORRECTO
    private val _text = MutableLiveData<String>().apply {
        value = "Texto inicial"
    }
    val text: LiveData<String> = _text
}
