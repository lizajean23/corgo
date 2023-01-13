package com.example.corgo1

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.viewpager2.widget.ViewPager2
import com.example.corgo1.adapter.ViewPagerFragmentAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class HomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerFragmentAdapter: ViewPagerFragmentAdapter
    private lateinit var bottomNavigationView: BottomNavigationView
    private val navigationItem = NavigationBarView.OnItemSelectedListener {
        when (it.itemId){
            R.id.feedFragment ->{
                viewPager.currentItem = 0
                return@OnItemSelectedListener true
            }
            R.id.postFragment ->{
                viewPager.currentItem = 1
                return@OnItemSelectedListener true
            }
            R.id.vaccineFragment ->{
                viewPager.currentItem = 2
                return@OnItemSelectedListener true
            }
            R.id.profileFragment ->{
                viewPager.currentItem = 3
                return@OnItemSelectedListener true
            }
        }
        false

    }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavView)
        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)
        viewPager.adapter = viewPagerFragmentAdapter
        bottomNavigationView.setOnItemSelectedListener(navigationItem)





        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> bottomNavigationView.menu.findItem(R.id.feedFragment).isChecked = true
                    1 -> bottomNavigationView.menu.findItem(R.id.postFragment).isChecked = true
                    2 -> bottomNavigationView.menu.findItem(R.id.vaccineFragment).isChecked = true
                    3 -> bottomNavigationView.menu.findItem(R.id.profileFragment).isChecked = true
                }
            }
        })

    }
    override fun onBackPressed() {
        if (viewPager.currentItem == 0){
            super.onBackPressed()

        }else{
            viewPager.currentItem = viewPager.currentItem - 1
        }

    }

}