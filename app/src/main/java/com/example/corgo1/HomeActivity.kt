package com.example.corgo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.corgo1.adapter.ViewPagerFragmentAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        bottomNavigationView = findViewById(R.id.bottomNavView)
        navController = findNavController(R.id.navigation_home_graph)
        bottomNavigationView.setupWithNavController(navController)







    }
}