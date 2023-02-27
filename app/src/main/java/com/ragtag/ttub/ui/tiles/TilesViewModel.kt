package com.ragtag.ttub.ui.tiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TilesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Tiles Fragment"
    }
    val text: LiveData<String> = _text
}