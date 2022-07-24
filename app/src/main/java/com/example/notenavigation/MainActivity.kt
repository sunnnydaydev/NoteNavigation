package com.example.notenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notenavigation.fragments.MainFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            //1、获取FragmentContainerView中name指定的fragment，并强转为NavHostFragment
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
           // 2、获取NavHostFragment的成员属性navController
        val navController = navHostFragment.navController

        // 3、 使用safe args 进行跳转
        btnTest.setOnClickListener {
            val action = MainFragmentDirections.actionFragmentMainToFragmentBlank(userName = "Serendipity")
            navController.navigate(action)
        }
    }
}