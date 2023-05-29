package com.example.cookbook

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {


    private val tabTitles = arrayOf("Main", "Main dishes")


    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return TopFragment()
            1 -> return DishListFragment()

        }
        return TopFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}