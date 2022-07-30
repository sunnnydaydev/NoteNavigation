package com.example.notenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.notenavigation.nest.ChooseRecipientFragmentDirections
import com.example.notenavigation.nest.HomeFragmentDirections

class NestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nest)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment

        val navController = navHostFragment.navController


        // 跳转到第二个导航图的起始目的地
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToSendMoneyGraph())

        navController.navigate(ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToChooseAmountFragment())


    }
}