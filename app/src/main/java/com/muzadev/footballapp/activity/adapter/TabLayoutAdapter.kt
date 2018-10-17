package com.muzadev.footballapp.activity.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class TabLayoutAdapter(
        private val fm: FragmentManager?,
        private val fragment: List<Fragment>,
        private val title: List<String>
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }

}