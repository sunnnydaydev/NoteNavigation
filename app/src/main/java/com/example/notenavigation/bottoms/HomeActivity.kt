package com.example.notenavigation.bottoms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notenavigation.R
import com.example.notenavigation.nest.HomeFragmentDirections
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val controller = navHostFragment.navController
        bottomNavigationView.setupWithNavController(controller)

    }
}