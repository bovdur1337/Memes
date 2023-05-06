package com.example.memes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.memes.R
import com.example.memes.db.MemeDatabase
import com.example.memes.viewmodel.MemeViewModel
import com.example.memes.viewmodel.MemeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val viewModel: MemeViewModel by lazy {
        val memeDatabase = MemeDatabase.getInstance(this)
        val memeViewModelProviderFactory = MemeViewModelFactory(memeDatabase)
        ViewModelProvider(this, memeViewModelProviderFactory)[MemeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}