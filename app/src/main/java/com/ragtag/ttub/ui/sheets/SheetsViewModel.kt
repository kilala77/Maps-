package com.ragtag.ttub.ui.sheets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SheetsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sheets Fragment"
    }
    val text: LiveData<String> = _text
}