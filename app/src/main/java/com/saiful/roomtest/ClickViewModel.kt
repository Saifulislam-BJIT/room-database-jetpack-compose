package com.saiful.roomtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ClickViewModel(private val dao: ClickDao) : ViewModel() {
    val click = dao.getClick()
    fun updateClick(click: Click) {
        viewModelScope.launch {
            dao.updateClick(click)
        }
    }
}