package com.muzadev.footballapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by zulfakar on 13/10/18.
 * For educational purposes
 */
class ViewPagerAdapter(private val fm: FragmentManager?, private val fragments: List<Fragment>, private val titles: List<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(postition: Int): Fragment {
        return fragments[postition]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}