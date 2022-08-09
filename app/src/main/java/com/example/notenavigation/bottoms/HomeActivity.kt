package com.example.notenavigation.bottoms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
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
        // 两控件联动
        bottomNavigationView.setupWithNavController(controller)
        // 想要主动添加监听也可
        controller.addOnDestinationChangedListener{controller,destination,args ->
             Log.i("HomeActivity","current destination:${destination.label}")
        }
    }
}