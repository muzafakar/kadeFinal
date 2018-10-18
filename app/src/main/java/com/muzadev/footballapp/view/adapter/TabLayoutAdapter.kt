package com.muzadev.footballapp.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class TabLayoutAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val fragment = mutableListOf<Fragment>()
    private val title = mutableListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        this.fragment.add(fragment)
        this.title.add(title)
    }

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