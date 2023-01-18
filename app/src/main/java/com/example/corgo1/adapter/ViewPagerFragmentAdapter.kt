package com.example.corgo1.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.corgo1.appFragments.FeedFragment
import com.example.corgo1.appFragments.PostFragment
import com.example.corgo1.appFragments.ProfileFragment
import com.example.corgo1.appFragments.VaccineFragment

class ViewPagerFragmentAdapter(activity:AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return FeedFragment()
        }
        else if (position == 1) {
            return PostFragment()
        }
        else if (position == 2) {
            return VaccineFragment()
        } else {
            return ProfileFragment()

        }
    }

}

