package com.example.corgo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.viewpager2.widget.ViewPager2
import com.example.corgo1.adapter.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerFragmentAdapter: ViewPagerFragmentAdapter
    private lateinit var tabLayout : TabLayout
    private val tabTitles = arrayListOf("Feed", "Post", "Vaccine", "Profile")


    override fun onCreate(savedInstanceState: Bundle?) {


    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    val actionBar: ActionBar? = supportActionBar
    actionBar?.hide()


    viewPager = findViewById(R.id.viewPager)
    tabLayout = findViewById(R.id.TabLayout)
    viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)
    viewPager.adapter = viewPagerFragmentAdapter

    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        tab.text = tabTitles[position]

    }.attach()


}

    override fun onBackPressed() {
            viewPager.currentItem = viewPager.currentItem - 1
    }

}