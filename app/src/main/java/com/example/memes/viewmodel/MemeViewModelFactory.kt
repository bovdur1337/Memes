package com.example.memes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memes.db.MemeDatabase

class MemeViewModelFactory(
    private val memeDatabase: MemeDatabase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MemeViewModel(memeDatabase) as T
    }
}